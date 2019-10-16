/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;

/**
 * @author long_
 *
 */
public abstract class AbstractLineTypeFlipAction extends AbstractPaintAction {
    public AbstractLineTypeFlipAction() {
        setPaintState();
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

    protected abstract void setPaintState();
}
