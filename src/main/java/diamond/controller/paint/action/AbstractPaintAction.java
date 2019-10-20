/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.context.PointedElement;
import diamond.controller.paint.state.PaintStateInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.view.screen.draw.OriLineDrawer;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriLineColor;

/**
 * @author long_
 *
 */
public abstract class AbstractPaintAction implements PaintActionInterface {
    private PaintStateInterface state;

    protected final void setActionState(PaintStateInterface state) {
        this.state = state;
    }

    protected final PaintStateInterface getActionState() {
        return state;
    }

    @Override
    public void destroy(Context context) {
        context.initialize();
    }

    @Override
    public void recover(Context context) {
    }

    @Override
    public PaintActionInterface onLeftClick(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Double currentLogicalMousePoint = paintScreenContext
                .getCurrentLogicalMousePoint();
        doAction(context, currentLogicalMousePoint);
        return this;
    }

    @Override
    public PaintActionInterface onRightClick(Context context) {
        undo(context);
        return this;
    }

    @Override
    public void doAction(Context context, Point2D.Double clickedPoint) {
        state = state.doAction(context, clickedPoint);
    }

    @Override
    public void undo(Context paintContext) {
        state = state.undo(paintContext);
    }

    @Override
    public Point2D.Double onMove(Context context) {
        state.setCandate(context);
        //        setCandidateVertexOnMove(context);
        //        setCandidateLineOnMove(context);

        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        return pointedElements.getOriPoint();
    }

    @Override
    public void onPress(Context context) {
    };

    @Override
    public void onDrag(Context context) {
    };

    @Override
    public void onRelease(Context context) {
    };

    protected void drawTemporaryLine(Graphics2D g2d,
            PaintScreenContext context) {
        PickedElements pickedElements = context.getPickedElements();
        Stack<OriPoint> oriPoints = pickedElements.getOriPoints();
        if (oriPoints.size() > 0) {
            OriPoint picked = oriPoints.peek();
            OriLineDrawer.drawLine(g2d,
                    new OriLine(new OriPoint(picked.x, picked.y),
                            context.getCandidateOriPoint(),
                            LineType.CREASE),
                    OriLineColor.PICKED,
                    LineStyle.STROKE_TEMPORARY);
        }

    }

}
