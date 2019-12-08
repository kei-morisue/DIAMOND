/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.controller.Context;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeModifier {

    public static void settle(Context context, HalfEdge he) {
        Face face = he.getFace();
        HalfEdge h0 = null;
        HalfEdge h1 = null;
        Vertex v0 = he.getV0();
        Vertex v1 = he.getV1();
        for (HalfEdge h : face.getHalfEdges()) {
            if (h.getV0() == v0) {
                h1 = h;
            }
            if (h.getV0() == v1) {
                h0 = h;
            }
        }
        if (h0 == null && h1 == null) {
            return;
        }
        if (h0 != null && h1 != null) {
            FaceSplitter.split(context.getCp(), face, he, h0, h1);
            return;
        }
        if (h0 != null) {
            FaceCutter.cut(face, he.getPair());
        } else {
            FaceCutter.cut(face, he);
        }
    }

    public static void unSettle(Context context, HalfEdge he) {
        Face f0 = he.getFace();
        HalfEdge hP = he.getPair();
        Face f1 = hP.getFace();
        if (f0 != f1) {
            FaceMarger.marge(context.getCp(), he);
        } else {
            if (he.getNext() == hP) {
                FaceMarger.unCut(he);
            } else if (hP.getNext() == he) {
                FaceMarger.unCut(hP);
            }
        }
    }
}
