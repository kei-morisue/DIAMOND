/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.PaintContext;
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
    public Point2D.Double onMove(PaintContext context) {
        setCandidateLineOnMove(context);
        OriLine pointedOriLine = context.getPointedOriLine();
        if (pointedOriLine != null) {
            context.setPointedOriPoint(new OriPoint());
            DistanceUtil.distancePointToSegment(
                    context.getCurrentLogicalMousePoint(),
                    pointedOriLine.p0,
                    pointedOriLine.p1,
                    context.getPointedOriPoint());
        }
        return context.getPointedOriPoint();
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedPoint(g2d, context);
    }

}
