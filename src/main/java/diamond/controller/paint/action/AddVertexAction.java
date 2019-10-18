/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.AddVertexState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.DistanceUtil;

/**
 * @author long_
 *
 */
public class AddVertexAction extends AbstractPaintAction {

    public AddVertexAction() {
        setActionState(new AddVertexState());
    }

    @Override
    public Point2D.Double onMove(Context context) {
        setCandidateLineOnMove(context);
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine pointedOriLine = paintScreenContext.getPointedOriLine();
        if (pointedOriLine != null) {
            paintScreenContext.setPointedOriPoint(new OriPoint());
            DistanceUtil.distancePointToSegment(
                    paintScreenContext.getCurrentLogicalMousePoint(),
                    pointedOriLine.p0,
                    pointedOriLine.p1,
                    paintScreenContext.getPointedOriPoint());
        }
        return paintScreenContext.getPointedOriPoint();
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        drawPointedPoint(g2d, context.getPaintScreenContext());
    }

}
