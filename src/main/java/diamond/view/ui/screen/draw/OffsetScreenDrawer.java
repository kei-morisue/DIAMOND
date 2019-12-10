/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class OffsetScreenDrawer {

    public static void draw(Graphics2D g2d, Context context) {
        AffineTransform transform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        int height = G2DUtil.getDimension(g2d).height;
        int width = G2DUtil.getDimension(g2d).width;
        setBG(g2d, width, height);
        drawCrossLines(g2d, height, width);
        drawFolded(g2d, context);
        g2d.setTransform(transform);
    }

    private static void drawFolded(Graphics2D g2d, Context context) {
        Cp cp = context.getCp();
        ScreenTransform transform = cp.getTransform();
        Rectangle bounds = g2d.getClip().getBounds();
        int height = bounds.height;
        int width = bounds.width;
        ScreenTransform screenTransform = new ScreenTransform(width, height);
        screenTransform.zoom(transform.getScale());
        screenTransform.rotate(transform.getTheta());

        Point2D.Double v = context.getPicker().getVertices().get(0).getFolded();

        screenTransform.translate(-v.x, -v.y);
        g2d.setTransform(screenTransform);
        FoldedScreenDrawer.draw(g2d, cp);
    }

    private static void drawCrossLines(Graphics2D g2d, int height, int width) {
        g2d.setColor(Color.red);
        g2d.drawLine(0, height >> 1, width, height >> 1);
        g2d.drawLine(width >> 1, 0, width >> 1, height);
    }

    private static void setBG(Graphics2D g2d, int width, int height) {
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, width, height);
    }
}
