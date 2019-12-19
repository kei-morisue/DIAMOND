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
public class PerpendicularUtil {
    public static Point2D.Double foot(Point2D.Double p,
            Point2D.Double p0,
            Point2D.Double p1) {
        double t = Point2DUtil.perFoot(p, p0, p1);
        return Point2DUtil.split(p0, p1, t);
    }
}
