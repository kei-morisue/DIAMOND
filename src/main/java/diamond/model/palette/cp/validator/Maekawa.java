/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.validator;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.orimodel.OriEdge;
import diamond.model.geom.element.orimodel.OriVertex;

/**
 * @author long_
 *
 */
public class Maekawa {
    public static boolean isMaekawa(OriVertex v) {
        int mountainCount = 0;
        int valleyCount = 0;
        for (OriEdge e : v.getEdges()) {
            if (e.getType() == LineType.MOUNTAIN) {
                mountainCount++;
            } else if (e.getType() == LineType.VALLEY) {
                valleyCount++;
            } else if (e.getType() == LineType.CUT) {
                return true;
            }
        }
        if (Math.abs(mountainCount - valleyCount) != 2) {
            System.out.println("edge type count invalid: " + v + " "
                    + Math.abs(mountainCount - valleyCount));
            return false;
        }
        return true;
    }
}
