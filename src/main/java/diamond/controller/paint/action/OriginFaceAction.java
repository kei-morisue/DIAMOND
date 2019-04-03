/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AnyPointPickkingState;

/**
 * @author long_
 *
 */
public class OriginFaceAction extends AbstractPaintAction {
    public OriginFaceAction() {
        setActionState(new AnyPointPickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedFace(g2d, context);
    }
}
