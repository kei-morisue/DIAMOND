/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriModelUtil;
import diamond.model.geom.element.origami.OriVertex;
import diamond.view.screen.draw.style.color.StringColor;

/**
 * @author long_
 *
 */
public class StringDrawer {

    public static void describe(Graphics2D g2d, Object pointed, int x, int y) {
        g2d.setColor(
                diamond.view.screen.draw.style.color.StringColor.DEBUGGING_STRING);
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

    public static void drawStepNo(Graphics2D g2d, int step, Font font) {
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setFont(font);
        g2d.setTransform(new AffineTransform());
        g2d.setColor(StringColor.STEP_NO);
        g2d.drawString(String.valueOf(step), 10, font.getSize());
        g2d.setTransform(tmpTransform);
    }

    public static void drawFaceNo(Graphics2D g2d, OriFace oriFace,
            List<OriFace> faces) {
        OriVertex centerPoint = OriModelUtil.getCenterPoint(oriFace);
        double scaleY = g2d.getTransform().getScaleY();
        g2d.setFont(new Font("Arial", Font.BOLD, (int) (10 / scaleY)));
        g2d.setColor(Color.BLACK);
        g2d.drawString(
                String.valueOf(faces.indexOf(oriFace)),
                (int) centerPoint.x, (int) centerPoint.y);
    }

}
