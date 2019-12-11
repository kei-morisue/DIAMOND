/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class VertexRemover {
    public static void remove(Cp cp, Vertex v) {
        if (v.getHalfEdges().size() != 2) {
            return;
        }
        HalfEdge h0 = v.getHalfEdges().get(0);
        HalfEdge h1 = v.getHalfEdges().get(1);
        if (h0.getType() != h1.getType()) {
            return;
        }
        Vertex v0 = h0.getV1();
        Vertex v1 = h1.getV1();
        if (CrossPointUtil.isDet0(h0.getV0(), v0, h1.getV0(),
                v1)) {
            HalfEdge h0P = h0.getPair();
            v0.remove(h0P);
            HalfEdge h1P = h1.getPair();
            v1.remove(h1P);
            HalfEdge h = new HalfEdge(v0, v1, h0.getType());

            h.connectTo(h1.getNext());
            h0P.getPrev().connectTo(h);

            h.getPair().connectTo(h0.getNext());
            h1P.getPrev().connectTo(h.getPair());

            Face f0 = h1.getFace();
            Face f1 = h0.getFace();
            if (f0 != null) {
                f0.remove(h1);
                f0.remove(h0.getPair());
                f0.add(h);
            }
            if (f1 != null) {
                f1.remove(h0);
                f1.remove(h1.getPair());
                f1.add(h.getPair());
            }

        }
    }
}
