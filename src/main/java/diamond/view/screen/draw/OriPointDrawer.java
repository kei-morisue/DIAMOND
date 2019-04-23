/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * @author long_
 *
 */
public class OriPointDrawer {

    public static void drawPoint(
            Graphics2D g2d,
            Point2D.Double point,
            double size, Color color) {
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(
                point.x - size * 0.5,
                point.y - size * 0.5,
                size,
                size));
    }

}
