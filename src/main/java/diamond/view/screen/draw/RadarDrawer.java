/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.view.screen.draw.style.LineStyle;

/**
 * @author long_
 *
 */
public class RadarDrawer {
    public static void draw(Graphics2D g2d, PaintContext context) {
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        int height = getDimension(g2d).height;
        int width = getDimension(g2d).width;
        setBG(g2d, width, height);
        int r = Math.min(width >> 1, height >> 1);
        drawDiscs(g2d, height, width, r);
        drawCrossLines(g2d, height, width);
        drawPointer(g2d, context);
        g2d.setTransform(transform);
    }

    private static void drawDiscs(Graphics2D g2d, int height, int width,
            int r) {
        g2d.setColor(Color.red);
        g2d.setStroke(LineStyle.STROKE_RADAR);
        drawDisc(g2d, height, width, r);
        drawDisc(g2d, height, width, r >> 1);
        drawDisc(g2d, height, width, r >> 2);
        drawDisc(g2d, height, width, r >> 3);
    }

    private static void drawCrossLines(Graphics2D g2d, int height, int width) {
        g2d.drawLine(0, height >> 1, width, height >> 1);
        g2d.drawLine(width >> 1, 0, width >> 1, height);
    }

    private static Dimension getDimension(Graphics2D g2d) {
        int height = g2d.getClip().getBounds().height;
        int width = g2d.getClip().getBounds().width;
        double scale = g2d.getTransform().getScaleX();
        height *= scale;
        width *= scale;
        return new Dimension(width, height);
    }

    private static void drawDisc(Graphics2D g2d, int height, int width, int r) {
        g2d.drawOval((width >> 1) - r, (height >> 1) - r, r << 1, r << 1);
    }

    private static void setBG(Graphics2D g2d, int width, int height) {
        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(0, 0, width, height);
    }

    private static void drawPointer(
            Graphics2D g2d,
            PaintContext context) {
        Double p = context.currentLogicalMousePoint;
        g2d.setTransform(new AffineTransform());
        int height, width;
        height = getDimension(g2d).height;
        width = getDimension(g2d).width;
        if (p != null) {
            g2d.setColor(Color.green);
            g2d.setStroke(LineStyle.STROKE_OFFSET);
            g2d.drawLine(
                    width >> 1,
                    height >> 1,
                    (width >> 1) + (int) p.x,
                    (height >> 1) + (int) p.y);
        }
    }

}
