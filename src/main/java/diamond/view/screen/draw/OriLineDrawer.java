/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class OriLineDrawer {

    public static void drawLine(Graphics2D g2d, OriLine line, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                line.p1.y));
    }

}
