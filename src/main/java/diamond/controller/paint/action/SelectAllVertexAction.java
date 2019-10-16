/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.selectv.AllOriPointPickkingState;

/**
 * @author long_
 *
 */
public class SelectAllVertexAction extends AbstractPaintAction {

    public SelectAllVertexAction() {
        setActionState(new AllOriPointPickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedPoints(g2d, context);
    }

}
