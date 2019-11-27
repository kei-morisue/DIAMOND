/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.math;

import java.awt.geom.Point2D;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.Point2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeFinder {
    public static HalfEdge find(Context context) {
        double min = Double.MAX_VALUE;
        HalfEdge candidate = null;
        Point2D.Double p = context.getMousePoint();
        for (HalfEdge he : context.getCp().getHalfEdges()) {
            double dist = Point2DUtil.distanceToSegment(p, he.getV0(),
                    he.getV1());
            if (dist < min) {
                min = dist;
                candidate = he;
            }
        }
        double scale = context.getPaintScreen().getTransform().getScale();
        if (min < Config.EPSILON_SCREEN / scale) {
            return candidate;
        } else {
            return null;
        }
    }
}
