/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.axiom4.OriLine0PickkingState;
import diamond.controller.paint.state.axiom4.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class Axiom4Action extends AbstractPaintAction {

    public Axiom4Action() {
        setActionState(
                new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedPoints(g2d, context);
        drawPointedPoint(g2d, context);
        if (getActionState().getClass() == OriLine0PickkingState.class) {
            drawPointedLine(g2d, context);
        } else {
            drawPointedPoint(g2d, context);
        }
    }
}
