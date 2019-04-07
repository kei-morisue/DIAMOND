/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.topface.OriFace0PickkingState;

/**
 * @author long_
 *
 */
public class FaceOrderingAction extends AbstractPaintAction {
    public FaceOrderingAction() {
        setActionState(new OriFace0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedFace(g2d, context);
    }

    @Override
    public Point2D.Double onMove(PaintContext context) {
        setCandidateFaceOnMove(context);
        return context.pointedOriPoint;
    }
}
