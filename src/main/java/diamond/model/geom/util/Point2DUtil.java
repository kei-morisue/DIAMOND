/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Collection;

/**
 * @author long_
 *
 */
public class Point2DUtil {
    public static double diatance(Double p0, Double p1) {
        return Point2D.distance(p0.x, p0.y, p1.x, p1.y);
    }

    public static double angle(Double p0) {
        return Math.atan2(p0.y, p0.x);
    }

    public static Double scale(Double p0, double scale) {
        return new Double(p0.x * scale, p0.y * scale);
    }

    public static Double sub(Double p0, Double p1) {
        return new Double(p0.x - p1.x, p0.y - p1.y);
    }

    public static Double plus(Double p0, Double p1) {
        return new Double(p0.x + p1.x, p0.y + p1.y);
    }

    public static Double build(double r, double angle) {
        return new Double(r * Math.cos(angle), r * Math.sin(angle));
    }

    public static Double center(Double p0, Double p1) {
        return scale(plus(p0, p1), 0.5);
    }

    public static Double center(Collection<? extends Double> ps) {
        Double sum = new Double();
        for (Double p : ps) {
            sum = plus(sum, p);
        }
        return scale(sum, 1 / ps.size());
    }

    public static Double rotate(Double p, double angle) {
        Double result = new Double();
        AffineTransform.getRotateInstance(angle).transform(p, result);
        return result;
    }

    public static Double pivotScale(Double p0, Double p1, double scale) {
        Double center = Point2DUtil.center(p0, p1);
        return Point2DUtil.plus(center,
                Point2DUtil.scale(Point2DUtil.sub(p0, p1), scale / 2));
    }

}
