/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action.arrow;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.AbstractPaintAction;
import diamond.controller.paint.state.arrow.ValleyArrowPaintState;

/**
 * @author long_
 *
 */
public class ValleyArrowPaintAction extends AbstractPaintAction {
    public ValleyArrowPaintAction() {
        setActionState(new ValleyArrowPaintState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedLine(g2d, context);

    }

}
