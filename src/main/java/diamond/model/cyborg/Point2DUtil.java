/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.Point2D;

/**
 * @author Kei Morisue
 *
 */
public class Point2DUtil {
    public static Point2D.Double plus(Point2D.Double p0, Point2D.Double p1) {
        return new Point2D.Double(p0.x + p1.x, p0.y + p1.y);
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

    public static Point2D.Double middle(Point2D.Double p0, Point2D.Double p1) {
        return scale(plus(p0, p1), (0.5));
    }

}
