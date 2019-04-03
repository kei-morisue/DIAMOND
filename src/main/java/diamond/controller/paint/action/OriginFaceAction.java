/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriFacePickkingState;

/**
 * @author long_
 *
 */
public class OriginFaceAction extends AbstractPaintAction {
    public OriginFaceAction() {
        setActionState(new OriFacePickkingState());
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
