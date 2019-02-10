/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.view.resource.color.ColorStyle;

/**
 * @author long_
 *
 */
public class OriDrawer {
    public static void describe(Graphics2D g2d, Object pointed, int x, int y) {
        g2d.setColor(ColorStyle.DEBUGGING_STRING);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        if (pointed == null) {
            g2d.drawString("-", x, y);
            ;
        } else {
            g2d.drawString(pointed.toString(), x, y);
        }
        g2d.setTransform(tmpTransform);
        return;
    }

    public static void drawLine(Graphics2D g2d, OriLine line, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                line.p1.y));
    }

    public static void drawLine(Graphics2D g2d, OriHalfEdge he, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        Vector2d p0 = he.getVertex().getP();
        Vector2d p1 = he.getNext().getVertex().getP();
        g2d.draw(new Line2D.Double(p0.x, p0.y, p1.x, p1.y));
    }

    public static void drawPoint(Graphics2D g2d, Vector2d point,
            double size, Color color) {
        double scaledSize = size;
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(
                point.x - scaledSize * 0.5,
                point.y - scaledSize * 0.5,
                scaledSize,
                scaledSize));
    }

    public static void drawFace(Graphics2D g2d,
            OriFace face, Color color) {
        g2d.setColor(color);
        g2d.fill(face.preOutline);
    }
}
