/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.faceorder.OriFace0PickkingState;

/**
 * @author long_
 *
 */
public class FaceOrderingAction extends AbstractPaintAction {
    public FaceOrderingAction() {
        setActionState(new OriFace0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        drawPointedFace(g2d, paintScreenContext);
        drawPickedFaces(g2d, paintScreenContext);
    }

    @Override
    public Point2D.Double onMove(Context context) {
        setCandidateFaceOnMove(context);
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        return paintScreenContext.getPointedOriPoint();
    }

}
