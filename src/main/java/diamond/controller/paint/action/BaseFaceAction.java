/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.baseface.BaseFacePickkingState;

/**
 * @author long_
 *
 */
public class BaseFaceAction extends AbstractPaintAction {
    public BaseFaceAction() {
        setActionState(new BaseFacePickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        drawPointedFace(g2d, context.getPaintScreenContext());
    }

    @Override
    public Point2D.Double onMove(Context context) {
        setCandidateFaceOnMove(context);
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        return paintScreenContext.getPointedOriPoint();
    }
}
