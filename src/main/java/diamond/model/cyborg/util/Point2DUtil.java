/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;

/**
 * @author Kei Morisue
 *
 */
public class Point2DUtil {
    public static Point2D.Double plus(Point2D.Double p0, Point2D.Double p1) {
        return new Point2D.Double(p0.x + p1.x, p0.y + p1.y);
    }

    public static Point2D.Double minus(Point2D.Double p0, Point2D.Double p1) {
        return new Point2D.Double(p0.x - p1.x, p0.y - p1.y);
    }

    public static Point2D.Double scale(Point2D.Double p, double scale) {
        return new Point2D.Double(p.x * scale, p.y * scale);
    }

    public static Point2D.Double scale(Point2D.Double p, Point2D.Double pivot,
            double scale) {
        return new Point2D.Double(
                p.x * scale + pivot.x * (1.0 - scale),
                p.y * scale + pivot.y * (1.0 - scale));
    }

    public static double prod(Point2D.Double p0, Point2D.Double p1) {
        return p0.x * p1.x + p0.y * p1.y;
    }

    public static double distanceToSegment(
            Point2D.Double p,
            Point2D.Double sp,
            Point2D.Double ep) {
        Point2D.Double a = minus(p, sp);
        Point2D.Double b = minus(ep, sp);
        double t = prod(b, a) / prod(b, b);
        if (t < 0.0) {
            return p.distance(sp);
        } else if (t > 1.0) {
            return p.distance(ep);
        } else {
            return p.distance(plus(sp, scale(b, t)));
        }
    }
}
