/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.axiom2.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class Axiom2Action extends AbstractPaintAction {
    public Axiom2Action() {
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedLines(g2d, context);
        drawPickedPoints(g2d, context);
        drawPointedPoint(g2d, context);
    }
}
