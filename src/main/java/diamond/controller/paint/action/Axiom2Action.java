/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
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
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        drawPickedLines(g2d, paintScreenContext);
        drawPickedPoints(g2d, paintScreenContext);
        drawPointedPoint(g2d, paintScreenContext);
    }
}
