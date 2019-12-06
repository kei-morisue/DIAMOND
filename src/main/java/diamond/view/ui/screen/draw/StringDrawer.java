/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.util.CenterPointUtil;

/**
 * @author Kei Morisue
 *
 */
public class StringDrawer {
    private static final Color COLOR_STRING_COLOR = Color.black;
    final public static Font FONT_FOLDED_STEP = new Font("Arial", Font.BOLD,
            100);
    final public static Font FONT_REPEAT_STEP = new Font("Arial", Font.PLAIN,
            50);
    final public static Font FONT_DIAGRAM_STEP = new Font("Arial", Font.BOLD,
            40);

    public static void drawFoldedStepNo(Graphics2D g2d, int step, int x,
            int y) {
        g2d.setFont(FONT_FOLDED_STEP);
        draw(g2d, step, x, y);
    }

    public static void drawRepeatStepNo(Graphics2D g2d, int step, int x,
            int y) {
        g2d.setFont(FONT_REPEAT_STEP);
        draw(g2d, step, x, y);
    }

    public static void drawDiagramStepNo(Graphics2D g2d, int step, int x,
            int y) {
        g2d.setFont(FONT_DIAGRAM_STEP);
        draw(g2d, step, x, y);
    }

    private static void draw(Graphics2D g2d, int step, int x, int y) {
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        g2d.setColor(COLOR_STRING_COLOR);
        g2d.drawString(String.valueOf(step), 10 + x,
                g2d.getFont().getSize() + y);
        g2d.setTransform(tmpTransform);
    }

    public static void drawFaceNo(Graphics2D g2d, Face face,
            List<Face> faces) {
        Point2D.Double centerPoint = CenterPointUtil.get(face);
        double scaleY = g2d.getTransform().getScaleY();
        g2d.setFont(new Font("Arial", Font.BOLD, (int) (10 / scaleY)));
        g2d.setColor(Color.BLACK);
        g2d.drawString(
                String.valueOf(faces.indexOf(face)),
                (int) centerPoint.x, (int) centerPoint.y);
    }
}
