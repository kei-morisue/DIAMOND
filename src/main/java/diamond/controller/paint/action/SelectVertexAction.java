/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.selectv.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class SelectVertexAction extends AbstractPaintAction {

    public SelectVertexAction() {
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        drawPickedPoints(g2d, paintScreenContext);
        drawPointedPoint(g2d, paintScreenContext);
    }

}
