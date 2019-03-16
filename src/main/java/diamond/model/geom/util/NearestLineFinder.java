/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.palette.cp.CreasePattern;

/**
 * @author long_
 *
 */
public class NearestLineFinder {
    public static OriLine findAround(
            PaintContext context) {
        double minDistance = Double.MAX_VALUE;
        OriLine candidate = null;
        CreasePattern creasePattern = context.getCP();
        Point2D.Double p = context.currentLogicalMousePoint;
        for (OriLine line : creasePattern.getLines()) {
            double dist = DistanceUtil.DistancePointToSegment(
                    p, line.p0, line.p1);
            if (dist < minDistance) {
                minDistance = dist;
                candidate = line;
            }
        }
        double scale = context.coordinateTransform.getScale();
        if (minDistance / scale < 10) {
            return candidate;
        } else {
            return null;
        }
    }
}
