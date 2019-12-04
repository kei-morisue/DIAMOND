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
public class BisectorUtil {
    private final static double NORM = 900.0;

    public static Point2D.Double[] perpendicular(Double p0, Double p1) {
        Point2D.Double[] pts = { p0, p1 };
        Double c = CenterPointUtil.get(p0, p1);
        Double q0 = Point2DUtil.per(Point2DUtil.sub(p0, c));
        Double q1 = Point2DUtil.per(Point2DUtil.sub(p1, c));

        pts[0] = Point2DUtil.add(c,
                Point2DUtil.scale(Point2DUtil.normalize(q0), NORM));
        pts[1] = Point2DUtil.add(c,
                Point2DUtil.scale(Point2DUtil.normalize(q1), NORM));
        return pts;
    }

    public static Point2D.Double angle(Double p0, Double p, Double p1,
            Double q0, Double q1) {
        Point2D.Double q = null;
        Double n0 = Point2DUtil.normalize(Point2DUtil.sub(p0, p));
        Double n1 = Point2DUtil.normalize(Point2DUtil.sub(p1, p));
        Double n = Point2DUtil.add(p, Point2DUtil
                .scale(Point2DUtil.normalize(Point2DUtil.add(n0, n1)), NORM));
        double[] ds = CrossPointUtil.getSplitter(p, n, q0, q1);
        if (ds == null) {
            return null;
        } else {
            return Point2DUtil.split(q0, q1, ds[1]);
        }
    }
}
