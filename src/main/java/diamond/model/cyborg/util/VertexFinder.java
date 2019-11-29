/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class VertexFinder {
    public static Vertex find(Context context) {
        double scale = context.getPaintScreen().getTransform().getScale();
        double boundary = Config.EPSILON_SCREEN / scale;
        Point2D.Double center = context.getMousePoint();
        Vertex nearest = null;
        double shortestDistance = boundary;
        for (Vertex v : context.getCp().getVertices()) {
            double d = v.distance(center);
            if (d < boundary) {
                if (nearest == null || d < shortestDistance) {
                    nearest = v;
                }
            }
        }
        return nearest;
    }
}
