/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeModifier {
    public static void flip(HalfEdge he) {
        EdgeType type = he.getType();
        he.setType(EdgeType.getPairType(type));
    }

    public static void settle(HalfEdge he) {
        Face face = he.getFace();
        HalfEdge h0 = null;
        HalfEdge h1 = null;
        for (HalfEdge h : face.getHalfEdges()) {
            if (he.getV0() == h.getV1()) {
                h0 = h;
            }
            if (he.getV1() == h.getV1()) {
                h1 = h;
            }
        }
        if (h0 == null && h1 == null) {
            return;
        }
        if (h0 != null && h1 != null) {
            FaceSplitter.split(face, he);
        }
        HalfEdge h = null;
        HalfEdge hP = he.getPair();
        if (h0 == null) {
            h = h1;
            h.connectTo(hP);
            hP.connectTo(he);
            he.connectTo(h.getNext());
        } else {
            h = h0;
            h.connectTo(he);
            he.connectTo(hP);
            hP.connectTo(h.getNext());
        }
        face.add(he);
        face.removeUnsettled(he);
        face.removeUnsettled(hP);
    }

    public static void unSettle(HalfEdge he) {
        EdgeType type = he.getType();
        he.setType(EdgeType.getAnotherType(type));
        FaceMarger.marge(he);
    }
}
