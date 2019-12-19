/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeRemover {
    public static void remove(Cp cp, HalfEdge he) {
        if (he.getType() == EdgeType.CUT) {
            return;
        }
        if (EdgeType.isSettled(he.getType())) {
            HalfEdgeModifier.unSettle(cp, he);
        }
        he.getFace().remove(he);
        Vertex v0 = he.getV0();
        Vertex v1 = he.getV1();
        v0.remove(he);
        v1.remove(he.getPair());
        VertexRemover.remove(cp, v0);
        VertexRemover.remove(cp, v1);
    }
}
