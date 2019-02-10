/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.symmetric.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class SymmetricLineAction extends AbstractPaintAction {

    public SymmetricLineAction() {
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedVertices(g2d, context);
        drawPointedVertex(g2d, context);
    }
}
