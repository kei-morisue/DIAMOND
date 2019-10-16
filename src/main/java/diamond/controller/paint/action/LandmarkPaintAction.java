/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.landmark.LandmarkPaintState;

/**
 * @author long_
 *
 */
public class LandmarkPaintAction extends AbstractPaintAction {
    public LandmarkPaintAction() {
        setActionState(new LandmarkPaintState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedPoint(g2d, context);
    }

}
