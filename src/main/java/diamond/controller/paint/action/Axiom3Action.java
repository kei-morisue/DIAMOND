/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.axiom3.OriLine0PickkingState;
import diamond.controller.paint.state.axiom3.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class Axiom3Action extends AbstractPaintAction {

    public Axiom3Action() {
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedLines(g2d, context);
        drawPickedPoints(g2d, context);
        drawPointedPoint(g2d, context);
        if (getActionState().getClass() == OriLine0PickkingState.class) {
            drawPointedLine(g2d, context);
        } else {
            drawPointedPoint(g2d, context);
        }
    }
}
