/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.HashSet;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceSplitter {
    public static void split(Cp cp, Face face, HalfEdge splitter, HalfEdge h0,
            HalfEdge h1) {
        face.remove(splitter);
        splitter.settle();
        Face f0 = new Face();
        Face f1 = new Face();
        Vertex v0 = splitter.getV0();
        Vertex v1 = splitter.getV1();
        open(f0, splitter, h0);
        open(f1, splitter.getPair(), h1);
        close(f0, v0, splitter);
        close(f1, v1, splitter.getPair());
        distribute(f0, f1, face);
        cp.add(f0);
        cp.add(f1);
        cp.remove(face);
    }

    private static void distribute(Face f0, Face f1, Face face) {
        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
        for (HalfEdge he : unsettledLines) {
            if (he.getProperty().isDisabled()) {
                continue;
            }
            if (FaceUtil.onFace(f0, he)) {
                f0.add(he);
            } else {
                f1.add(he);
            }
        }
    }

    private static void open(Face face, HalfEdge splitter, HalfEdge h0) {
        face.add(h0);
        splitter.connectTo(h0);
    }

    private static void close(Face face, Vertex endV, HalfEdge splitter) {
        HalfEdge h = face.getHalfEdges().get(0);
        do {
            h = h.getNext();
            face.add(h);
        } while (h.getV1() != endV);
        face.add(splitter);
        h.connectTo(splitter);
    }

}
