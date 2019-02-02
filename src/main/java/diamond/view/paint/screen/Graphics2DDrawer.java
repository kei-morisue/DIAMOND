/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;

import javax.vecmath.Vector2d;

import diamond.model.geom.OriLine;
import diamond.model.palette.cp.LineSetting;

/**
 * @author long_
 *
 */
public class Graphics2DDrawer {
    public static void showXnY(Graphics2D g2d, Vector2d candidate) {
        if (candidate == null) {
            return;
        }
        g2d.setColor(Color.BLACK);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.drawString(
                String.format("(%f, %f)", candidate.x, candidate.y),
                0,
                10);
        g2d.setTransform(tmpTransform);
        return;
    }

    public static void drawLines(Graphics2D g2d, Collection<OriLine> lines) {
        for (OriLine line : lines) {
            g2d.setColor(Color.RED);
            g2d.setStroke(LineSetting.STROKE_VALLEY);
            g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                    line.p1.y));
        }
    }

    public static void drawVertexRectangle(Graphics2D g2d, Vector2d v0,
            double size) {
        g2d.fill(new Rectangle2D.Double(
                v0.x - size * 0.5,
                v0.y - size * 0.5,
                size,
                size));

    }

    private static double calcScale(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        double scaledCosTheta = transform.getScaleX();
        double scaledSinTheta = transform.getShearY();
        return Math.hypot(scaledCosTheta, scaledSinTheta);
    }

}
