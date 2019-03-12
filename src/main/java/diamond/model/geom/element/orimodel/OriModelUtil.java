/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import java.awt.geom.Point2D;
import java.util.Collection;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class OriModelUtil {
    public static Vector2d getCenterPoint(Collection<OriHalfEdge> halfEdges) {
        Vector2d centerP = new Vector2d();
        for (OriHalfEdge he : halfEdges) {
            centerP.add(he.getSv());
        }
        centerP.scale(1.0 / halfEdges.size());
        return centerP;
    }

    public static Vector2d getCenterPoint(OriVertex v0, OriVertex v1) {
        Vector2d centerP = new Vector2d();
        centerP.add(v0);
        centerP.add(v1);
        centerP.scale(0.5);
        return centerP;
    }

    public static Point2D getScaledPoint(
            double scale,
            Vector2d centerP,
            OriVertex v) {
        return new Point2D.Double(
                v.x * scale + centerP.x * (1.0 - scale),
                v.y * scale + centerP.y * (1.0 - scale));
    }
}
