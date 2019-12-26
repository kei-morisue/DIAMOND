/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class FaceMarger {

    public static void marge(Cp cp, HalfEdge h0) {
        HalfEdge h1 = h0.getPair();
        margeConnect(h0, h1);
        Face face = new Face();
        Face f0 = buildFace(h0, face);
        Face f1 = buildFace(h1, face);
        h0.unSettle();
        face.add(h0);
        cp.remove(f0);
        cp.remove(f1);
        cp.add(face);
    }

    public static void unCut(HalfEdge he) {
        Face face = he.getFace();
        face.remove(he);
        HalfEdge hP = he.getPair();
        face.remove(hP);
        he.getPrev().connectTo(hP.getNext());
        he.unSettle();
        face.add(he);
    }

    private static Face buildFace(HalfEdge h0, Face face) {
        Face f0 = h0.getFace();
        f0.remove(h0);
        face.add(f0.getSortedEdges());
        face.add(f0.getUnsettledLines());
        return f0;
    }

    private static void margeConnect(HalfEdge h0, HalfEdge h1) {
        HalfEdge h0N = h0.getNext();
        HalfEdge h0P = h0.getPrev();
        HalfEdge h1N = h1.getNext();
        HalfEdge h1P = h1.getPrev();
        h1P.connectTo(h0N);
        h0P.connectTo(h1N);
    }
}
