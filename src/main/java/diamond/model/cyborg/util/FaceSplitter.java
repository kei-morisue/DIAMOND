/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceSplitter {
    public static Face[] split(Face face, HalfEdge h0) {
        Face f0 = new Face();
        Face f1 = new Face();
        Vertex v0 = h0.getV0();
        Vertex v1 = h0.getV1();
        for (HalfEdge he : face.getHalfEdges()) {
            if (he.getV0() == v1) {
                f0.open(he);
            } else if (he.getV0() == v0) {
                f1.open(he);
            }
        }
        ArrayList<HalfEdge> halfEdges0 = f0.getHalfEdges();
        ArrayList<HalfEdge> halfEdges1 = f1.getHalfEdges();
        if (halfEdges0.size() != 1 || halfEdges1.size() != 1) {
            return null;
        }

        buildLoop(f0, v0, h0);
        buildLoop(f1, v1, h0.getPair());

        //TODO split crease/unsettled lines
        Face[] faces = { f0, f1 };
        return faces;
    }

    private static void buildLoop(Face face, Vertex endV, HalfEdge splitter) {
        HalfEdge h = face.getHalfEdges().get(0).getNext();
        while (h.getV0() != endV) {
            face.add(h);
            h = h.getNext();
        }
        face.close(splitter);
    }

}
