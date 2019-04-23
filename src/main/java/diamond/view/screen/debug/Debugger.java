/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.debug;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.view.screen.draw.StringDrawer;
import diamond.view.screen.draw.style.FontStyle;

/**
 * @author long_
 *
 */
public class Debugger {
    public static void debugPaintContext(Graphics2D g2d,
            PaintContext paintContext) {
        g2d.setFont(FontStyle.DEBUG);
        describe(g2d, paintContext.currentLogicalMousePoint,
                "Current Logocal Point", 10);
        describe(g2d, paintContext.pointedOriLine, "Pointed Line", 20);
        describe(g2d, paintContext.pointedOriPoint, "Pointed Point", 30);
        describe(g2d, paintContext.pointedOriFace, "Pointed Face", 40);

        describe(g2d, paintContext.getPickedPoints(), "Picked Points", 60);
        describe(g2d, paintContext.getPickedLines(), "Picked Lines", 70);
        describe(g2d, paintContext.getPickedOriFaces(), "Picked Faces", 80);

        describe(g2d, paintContext.palette.getOriModel().getBaseFace(),
                "Base Face", 90);

        describe(g2d, paintContext.paintAction.getClass().getName(),
                "Paint Action", 110);

        describe(g2d, paintContext.palette.size(),
                "Steps", 120);
        describe(g2d, paintContext.palette.getStepNo(),
                "Focused Step No.", 130);
    }

    private static void describe(
            Graphics2D g2d,
            Object o,
            String s,
            int height) {
        if (o == null) {
            StringDrawer.describe(g2d, s + " : " + "null", 0, height);
        } else {
            StringDrawer.describe(g2d, s + " : " + o.toString(), 0, height);
        }
    }
}
