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
}
