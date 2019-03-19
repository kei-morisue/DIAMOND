/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.paint.screen.debug;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.view.paint.screen.draw.OriDrawer;

/**
 * @author long_
 *
 */
public class Debugger {
    public static void debugPaintContext(Graphics2D g2d,
            PaintContext paintContext) {
        describe(g2d, paintContext.currentLogicalMousePoint,
                "Current Logocal Point", 10);
        describe(g2d, paintContext.pointedOriLine, "Pointed Line", 20);
        describe(g2d, paintContext.pointedOriPoint, "Pointed Point", 30);
        describe(g2d, paintContext.getPickedPoints(), "Picked Points", 40);
        describe(g2d, paintContext.getCP().getOrigin(), "Origin Points", 50);

        describe(g2d, paintContext.getPickedLines(), "Picked Lines", 70);

        describe(g2d, paintContext.getScale(), "Scale", 80);
        describe(g2d, paintContext.paintAction.getClass().getName(),
                "Paint Action", 100);

        describe(g2d, paintContext.getCreasePatterns().size(),
                "Steps", 120);
    }

    private static void describe(
            Graphics2D g2d,
            Object o,
            String s,
            int height) {
        if (o == null) {
            OriDrawer.describe(g2d, s + " : " + "null", 0, height);
        } else {
            OriDrawer.describe(g2d, s + " : " + o.toString(), 0, height);
        }
    }
}
