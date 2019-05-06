/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class LineDivider {
    public static Collection<OriLine> divide(
            OriLine line,
            Point2D.Double v,
            Collection<OriLine> lines) {
        ArrayList<OriLine> divided = new ArrayList<>(2);

        if (DistanceUtil.distance(line.p0, v) < Constants.EPS
                || DistanceUtil.distance(line.p1, v) < Constants.EPS) {
            return null;
        }

        OriLine l0 = new OriLine(line.p0, v, line.getType());
        OriLine l1 = new OriLine(v, line.p1, line.getType());
        lines.remove(line);
        lines.add(l0);
        lines.add(l1);
        return divided;
    }
}
