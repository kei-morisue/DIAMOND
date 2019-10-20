/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PointedElement;
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
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriLine pointedOriLine = pointedElements.getOriLine();
        OriPoint oriPoint = pointedElements.getOriPoint();
        if (pointedOriLine != null) {
            pointedElements.setOriPoint(new OriPoint());
            DistanceUtil.distancePointToSegment(
                    paintScreenContext.getCurrentLogicalMousePoint(),
                    pointedOriLine.p0,
                    pointedOriLine.p1,
                    oriPoint);
        }
        return oriPoint;
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        pointedElements.draw(g2d);
    }

}
