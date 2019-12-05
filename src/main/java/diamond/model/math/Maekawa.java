/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.math;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class Maekawa {
    public static boolean isValid(Vertex v) {
        int mountainCount = 0;
        int valleyCount = 0;
        for (HalfEdge e : v.getHalfEdges()) {
            if (e.getType() == EdgeType.MOUNTAIN) {
                mountainCount++;
            } else if (e.getType() == EdgeType.VALLEY) {
                valleyCount++;
            } else if (e.getType() == EdgeType.CUT) {
                return true;
            }
        }
        if (mountainCount == 0 && valleyCount == 0) {
            return true;
        }
        if (Math.abs(mountainCount - valleyCount) != 2) {
            return false;
        }
        return true;
    }
}
