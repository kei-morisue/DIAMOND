/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import diamond.model.geom.Constants;
import diamond.model.geom.element.OriPoint;
import diamond.model.palette.CreasePatternHolder;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public class NearestPointFinder {
    public static OriPoint findAround(PaintContext context) {
        double boundary = Constants.CLOSE_THRESHOLD / context.getScale();
        OriPoint center = new OriPoint(context.currentLogicalMousePoint);
        OriPoint nearest = null;
        double shortestDistance = boundary;
        double shortestCandidate = boundary;
        for (OriPoint point : CreasePatternHolder.getCP().getPoints()) {
            shortestCandidate = point.distance(center);
            if (shortestCandidate < boundary) {
                if (nearest == null || shortestCandidate < shortestDistance) {
                    nearest = point;
                }
            }
        }
        return nearest;
    }
}