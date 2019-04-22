/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
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
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedPoints(g2d, context);
        drawPointedPoint(g2d, context);
    }

}
