/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.DeleteVertexState;

/**
 * @author long_
 *
 */
public class DeleteVertexAction extends AbstractPaintAction {

    public DeleteVertexAction() {
        setActionState(new DeleteVertexState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedPoint(g2d, context);
    }

}
