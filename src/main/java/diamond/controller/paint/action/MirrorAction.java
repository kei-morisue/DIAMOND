/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.mirror.OriLinePickkingState;

/**
 * @author long_
 *
 */
public class MirrorAction extends AbstractPaintAction {
    public MirrorAction() {
        setActionState(new OriLinePickkingState());
    }

    @Override
    public PaintActionInterface onRightClick(PaintContext context) {
        undo(context);
        return this;
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedLines(g2d, context);
        drawPointedLine(g2d, context);
    }

}
