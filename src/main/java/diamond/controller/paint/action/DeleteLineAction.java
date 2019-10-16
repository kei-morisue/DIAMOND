/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.DeleteLineState;

/**
 * @author long_
 *
 */
public class DeleteLineAction extends AbstractPaintAction {
    public DeleteLineAction() {
        super();
        setActionState(new DeleteLineState());
    }

    @Override
    public void onPress(PaintContext context) {
    }

    @Override
    public void onDrag(PaintContext context) {
    }

    @Override
    public void onRelease(PaintContext context) {
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedLine(g2d, context);

    }
}
