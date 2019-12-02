/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * @author Kei Morisue
 *
 */
public class Point2DUtil {
    public static Point2D.Double add(Point2D.Double p0, Point2D.Double p1) {
        return new Point2D.Double(p0.x + p1.x, p0.y + p1.y);
    }

    public static Point2D.Double sub(Point2D.Double p0, Point2D.Double p1) {
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

    public static double cross(Point2D.Double p0, Point2D.Double p1) {
        return p0.x * p1.y - p0.y * p1.x;
    }

    public static Point2D.Double split(Point2D.Double p0, Point2D.Double p1,
            double t) {
        return add(scale(p0, 1 - t), scale(p1, t));
    }

    public static Double per(Point2D.Double p) {
        return new Point2D.Double(p.y, -p.x);
    }

    public static Double normalize(Point2D.Double p) {
        return scale(p, 1.0 / p.distance(.0, .0));
    }

    public static double distanceToSegment(
            Point2D.Double p,
            Point2D.Double sp,
            Point2D.Double ep) {
        Point2D.Double a = sub(p, sp);
        Point2D.Double b = sub(ep, sp);
        double t = prod(b, a) / prod(b, b);
        if (t < 0.0) {
            return p.distance(sp);
        } else if (t > 1.0) {
            return p.distance(ep);
        } else {
            return p.distance(add(sp, scale(b, t)));
        }
    }
}
