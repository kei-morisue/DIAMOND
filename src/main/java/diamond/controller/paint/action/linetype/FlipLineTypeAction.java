/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action.linetype;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.AbstractPaintAction;
import diamond.controller.paint.state.linetype.FlipLineTypeState;

/**
 * @author long_
 *
 */
public class FlipLineTypeAction extends AbstractPaintAction {
    public FlipLineTypeAction() {
        super();
        setActionState(new FlipLineTypeState());
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
