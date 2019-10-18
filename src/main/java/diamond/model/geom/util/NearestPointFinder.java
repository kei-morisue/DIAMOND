/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class NearestPointFinder {
    public static OriPoint findAround(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        double scale = paintScreenContext.getTransform().getScale();
        double boundary = Constants.CLOSE_THRESHOLD / scale;
        OriPoint center = new OriPoint(
                paintScreenContext.getCurrentLogicalMousePoint());
        OriPoint nearest = null;
        double shortestDistance = boundary;
        for (OriLine line : context.getPalette().getCP().getLines()) {
            double d0 = line.p0.distance(center);
            double d1 = line.p1.distance(center);
            if (d0 < boundary) {
                if (nearest == null || d0 < shortestDistance) {
                    nearest = line.p0;
                }
            }
            if (d1 < boundary) {
                if (nearest == null || d1 < shortestDistance) {
                    nearest = line.p1;
                }
            }
        }
        return nearest;
    }

}