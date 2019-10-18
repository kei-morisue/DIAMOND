/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class NearestLineFinder {
    public static OriLine findAround(Context context) {
        double minDistance = Double.MAX_VALUE;
        OriLine candidate = null;
        Cp creasePattern = context.getPalette().getCP();
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Point2D.Double p = paintScreenContext
                .getCurrentLogicalMousePoint();
        for (OriLine line : creasePattern.getLines()) {
            double dist = DistanceUtil.distancePointToSegment(
                    p, line.p0, line.p1);
            if (dist < minDistance) {
                minDistance = dist;
                candidate = line;
            }
        }
        double scale = paintScreenContext.getTransform().getScale();
        if (minDistance / scale < 10) {
            return candidate;
        } else {
            return null;
        }
    }
}
