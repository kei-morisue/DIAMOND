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
        Face f0 = h0.getFace();
        Face f1 = h0.getPair().getFace();
        f0.remove(h0);
        f1.remove(h1);
        HalfEdge h0N = h0.getNext();
        HalfEdge h0P = h0.getPrev();
        HalfEdge h1N = h1.getNext();
        HalfEdge h1P = h1.getPrev();
        h1P.connectTo(h0N);
        h0P.connectTo(h1N);
        Face face = new Face();

        face.add(f0.getHalfEdges());
        face.add(f1.getHalfEdges());
        face.add(f0.getUnsettledLines());
        face.add(f1.getUnsettledLines());
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
}
