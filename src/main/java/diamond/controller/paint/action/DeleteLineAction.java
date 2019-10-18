/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
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
    public void onPress(Context context) {
    }

    @Override
    public void onDrag(Context context) {
    }

    @Override
    public void onRelease(Context context) {
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        drawPointedLine(g2d, context.getPaintScreenContext());

    }
}
