/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

/**
 * @author Kei Morisue
 *
 */
public class Point2DUtil {
    public static double norm(Point2D.Double p) {
        return Math.hypot(p.x, p.y);
    }

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

    public static Point2D.Double rotate(Point2D.Double p, double theta) {
        double sin = Math.sin(theta);
        double cos = Math.cos(theta);
        return new Point2D.Double(p.x * cos - p.y * sin,
                p.x * sin + p.y * cos);
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

    public static double angle(Point2D.Double p) {
        return Math.atan2(p.y, p.x);
    }

    public static double angle(Point2D.Double p0, Point2D.Double p1) {
        return angle(p1) - angle(p0);
    }

    public static double perFoot(Point2D.Double p,
            Point2D.Double p0,
            Point2D.Double p1) {
        Point2D.Double a = sub(p, p0);
        Point2D.Double b = sub(p1, p0);
        return prod(b, a) / prod(b, b);
    }

    public static double distanceToSegment(
            Point2D.Double p,
            Point2D.Double p0,
            Point2D.Double p1) {
        double t = perFoot(p, p0, p1);
        if (t < 0.0) {
            return p.distance(p0);
        } else if (t > 1.0) {
            return p.distance(p1);
        } else {
            return p.distance(add(p0, scale(sub(p1, p0), t)));
        }
    }

    public static Point2D.Double apply(
            Point2D.Double p,
            AffineTransform transform) {
        Point2D.Double q = new Point2D.Double(0, 0);
        transform.transform(p, q);
        return q;
    }

    public static Rectangle2D.Double clip(
            Point2D.Double p,
            AffineTransform transform) {
        Double q = Point2DUtil.apply(
                p,
                transform);
        double size = 50;
        return new Rectangle2D.Double(
                q.x - size / 2,
                q.y - size / 2,
                size,
                size);
    }
}
