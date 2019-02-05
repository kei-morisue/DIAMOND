/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.OriLine;
import diamond.model.geom.element.OriPoint;
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

    public static void drawLines(Graphics2D g2d, Collection<OriLine> lines,
            Color color, Stroke stroke) {
        for (OriLine line : lines) {
            drawLine(g2d, line, color, stroke);
        }
    }

    public static void drawLine(Graphics2D g2d, OriLine line, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                line.p1.y));
    }

    public static void drawPoints(Graphics2D g2d, Collection<OriPoint> points,
            double size, Color color) {
        for (OriPoint point : points) {
            drawPoint(g2d, point, size * calcScale(g2d), color);
        }
    }

    public static void drawPoint(Graphics2D g2d, Vector2d point,
            double size, Color color) {
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(
                point.x - size * 0.5,
                point.y - size * 0.5,
                size,
                size));
    }

    public static double calcScale(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        double scaledCosTheta = transform.getScaleX();
        double scaledSinTheta = transform.getShearY();
        return Math.hypot(scaledCosTheta, scaledSinTheta);
    }

}
