/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.axiom1.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class Axiom1Action extends AbstractPaintAction {
    public Axiom1Action() {
        super();
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        drawPickedLines(g2d, paintScreenContext);
        drawPickedPoints(g2d, paintScreenContext);
        drawTemporaryLine(g2d, paintScreenContext);
        drawPointedPoint(g2d, paintScreenContext);
    }

}
