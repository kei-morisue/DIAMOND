/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.HashSet;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceSplitter {
    public static Face[] split(Face face, HalfEdge splitter) {
        Face f0 = new Face();
        Face f1 = new Face();
        Vertex v0 = splitter.getV0();
        Vertex v1 = splitter.getV1();
        openLoop(face, f0, f1, splitter);
        if (f0.getHalfEdges().size() != 1 || f1.getHalfEdges().size() != 1) {
            return null;//TODO cut segment, non-convex faces
        }
        buildLoop(f0, v0, splitter);
        buildLoop(f1, v1, splitter.getPair());
        splitUnsettledLines(face, splitter);
        Face[] faces = { f0, f1 };
        return faces;
    }

    private static void splitUnsettledLines(Face face, HalfEdge splitter) {
        //TODO split crease/unsettled lines
        HashSet<Vertex> crossPoints = new HashSet<Vertex>();

        for (HalfEdge he : face.getUnsettledLines()) {
            if (he == splitter) {
                continue;
            }
            Vertex v = HalfEdgeSplitter.split(splitter, he);
            if (v != null) {
                crossPoints.add(v);
            }
        }
        for (Vertex v : crossPoints) {
            //HalfEdgeSplitter.split(splitter, splitter, v);//TODO
        }

    }

    private static void openLoop(Face face, Face f0, Face f1,
            HalfEdge splitter) {
        Vertex v0 = splitter.getV0();
        Vertex v1 = splitter.getV1();
        for (HalfEdge he : face.getHalfEdges()) {
            if (he.getV0() == v1) {
                f0.open(he);
                splitter.connectTo(he);
            } else if (he.getV0() == v0) {
                f1.open(he);
                splitter.getPair().connectTo(he);
            }
        }
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
