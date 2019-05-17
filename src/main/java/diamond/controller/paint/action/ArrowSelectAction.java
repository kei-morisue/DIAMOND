/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.arrow.ArrowSelectState;

/**
 * @author long_
 *
 */
public class ArrowSelectAction extends AbstractPaintAction {

    public ArrowSelectAction() {
        setActionState(new ArrowSelectState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedLine(g2d, context);
    }
}
