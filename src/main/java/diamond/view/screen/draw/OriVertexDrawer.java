/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.draw.style.VertexStyle;

/**
 * @author long_
 *
 */
public class OriVertexDrawer {

    public static void drawVertex(Graphics2D g2d, OriVertex v, double size) {
        g2d.setColor(VertexStyle.COLOR_SELECTED);
        Point2D foldedPosition = v.getFoldedPosition();
        Double offset = v.getOffset();
        g2d.fill(new Rectangle2D.Double(
                foldedPosition.getX() + offset.x - size * 0.5,
                foldedPosition.getY() + offset.y - size * 0.5,
                size,
                size));
    }

}
