/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
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

    public static void settle(Context context, HalfEdge he) {
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
        Cp cp = context.getCp();

        if (h0 != null && h1 != null) {
            he.setType(EdgeType.getAnotherType(he.getType()));
            Face[] split = FaceSplitter.split(face, he);
            cp.add(split[0]);
            cp.add(split[1]);
            cp.remove(face);
            return;
        }
        if (h0 != null) {
            he.getPair().connectTo(h0.getNext());
            h0.connectTo(he);
            he.connectTo(he.getPair());
        } else {
            he.connectTo(h1.getNext());
            h1.connectTo(he.getPair());
            he.getPair().connectTo(he);
        }
        face.add(he);
        face.add(he.getPair());
        face.removeUnsettled(he);
        he.setType(EdgeType.getAnotherType(he.getType()));
    }

    public static void unSettle(Context context, HalfEdge he) {
        EdgeType type = he.getType();
        he.setType(EdgeType.getAnotherType(type));
        Face f0 = he.getFace();
        Face f1 = he.getPair().getFace();
        Cp cp = context.getCp();
        if (f0 != f1) {
            cp.add(FaceMarger.marge(he));
            cp.remove(f0);
            cp.remove(f1);
        } else {
            if (he.getNext() == he.getPair()) {
                f0.addUnsettled(he);
                f0.remove(he);
                he.getPrev().connectTo(he.getPair().getNext());
            } else if (he.getPair().getNext() == he) {
                f0.addUnsettled(he);
                f0.remove(he);
                he.getPair().getPrev().connectTo(he.getNext());
            }
        }
    }
}
