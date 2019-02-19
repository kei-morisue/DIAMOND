/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

/**
 * @author long_
 *
 */
public class Point2DUtil {
    public static double diatance(Point2D.Double p0, Point2D.Double p1) {
        return Point2D.distance(p0.x, p0.y, p1.x, p1.y);
    }

    public static double angle(Point2D.Double p0) {
        return Math.atan2(p0.y, p0.x);
    }

    public static Point2D.Double scale(Point2D.Double p0, double scale) {
        return new Point2D.Double(p0.x * scale, p0.y * scale);
    }

    public static Point2D.Double sub(Point2D.Double p0, Point2D.Double p1) {
        return new Point2D.Double(p0.x - p1.x, p0.y - p1.y);
    }

    public static Point2D.Double plus(Point2D.Double p0, Point2D.Double p1) {
        return new Point2D.Double(p0.x + p1.x, p0.y + p1.y);
    }

    public static Point2D.Double build(double r, double angle) {
        return new Point2D.Double(r * Math.cos(angle), r * Math.sin(angle));
    }
}
