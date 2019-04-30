/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.arrow.ValleyFoldArrowAddingState;

/**
 * @author long_
 *
 */
public class ValleyFoldArrowAddingAction extends AbstractPaintAction {
    public ValleyFoldArrowAddingAction() {
        setActionState(new ValleyFoldArrowAddingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedLine(g2d, context);

    }

}
