/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriHalfEdge;

/**
 * @author long_
 *
 */
public class OriHalfEdgeUtil {
    public static boolean isSameLineSegment(OriLine l, OriHalfEdge he) {
        return OriLineUtil.isSameLineSegment(l,
                new OriLine(he.getSv(), he.getEv(), null));
    }

}
