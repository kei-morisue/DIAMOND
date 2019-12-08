/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceCutter {
    public static void cut(Face f, HalfEdge cutter) {
        f.remove(cutter);
        cutter.settle();
        Vertex v0 = cutter.getV0();
        HalfEdge h0 = open(f, v0);
        if (h0 == null) {
            return;
        }
        HalfEdge hP = cutter.getPair();
        hP.connectTo(h0.getNext());
        h0.connectTo(cutter);
        cutter.connectTo(hP);
        f.add(cutter);
        f.add(hP);
    }

    private static HalfEdge open(Face face, Vertex v0) {
        for (HalfEdge he : face.getHalfEdges()) {
            if (he.getV1() == v0) {
                return he;
            }
        }
        return null;
    }
}
