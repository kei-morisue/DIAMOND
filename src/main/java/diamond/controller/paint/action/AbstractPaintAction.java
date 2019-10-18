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
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.util.NearestFaceFinder;
import diamond.model.geom.util.NearestLineFinder;
import diamond.model.geom.util.NearestPointFinder;
import diamond.view.screen.draw.OriFaceDrawer;
import diamond.view.screen.draw.OriLineDrawer;
import diamond.view.screen.draw.OriPointDrawer;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.VertexStyle;
import diamond.view.screen.draw.style.color.OriFaceColor;
import diamond.view.screen.draw.style.color.OriLineColor;
import diamond.view.screen.draw.style.color.OriPointColor;

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
        setCandidateVertexOnMove(context);
        setCandidateLineOnMove(context);

        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        return pointedElements.getOriPoint();
    }

    protected final void setCandidateVertexOnMove(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriPoint findAround = NearestPointFinder.findAround(context);
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        pointedElements.setOriPoint(findAround);

    }

    protected final void setCandidateLineOnMove(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine findAround = NearestLineFinder.findAround(context);
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        pointedElements.setOriLine(findAround);
    }

    protected final void setCandidateFaceOnMove(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriFace oriFace = pointedElements.getOriFace();
        if (oriFace != null) {
            oriFace.isPointed = false;
        }
        OriFace findAround = NearestFaceFinder.findAround(context);
        paintScreenContext.getPointedElements().setOriFace(findAround);
        if (oriFace != null) {
            oriFace.isPointed = true;
        }
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

    protected void drawPickedLines(Graphics2D g2d, PaintScreenContext context) {
        PickedElements pickedElements = context.getPickedElements();
        Stack<OriLine> oriLines = pickedElements.getOriLines();
        for (int i = 0; i < oriLines.size(); i++) {
            OriLineDrawer.drawLine(
                    g2d,
                    oriLines.get(i),
                    OriLineColor.PICKED,
                    LineStyle.STROKE_PICKED);
        }
    }

    protected void drawPickedPoints(Graphics2D g2d,
            PaintScreenContext context) {
        PickedElements pickedElements = context.getPickedElements();
        Stack<OriPoint> oriPoints = pickedElements.getOriPoints();
        for (int i = 0; i < oriPoints.size(); i++) {
            OriPoint point = oriPoints.get(i);
            OriPointDrawer.drawPoint(
                    g2d, point,
                    VertexStyle.SIZE_PICKED / context.getTransform().getScale(),
                    OriPointColor.ORIPOINT_PICKED);
        }
    }

    protected void drawPointedPoint(Graphics2D g2d,
            PaintScreenContext context) {
        PointedElement pointedElements = context.getPointedElements();
        OriPoint oriPoint = pointedElements.getOriPoint();
        if (oriPoint != null) {
            OriPointDrawer.drawPoint(
                    g2d,
                    oriPoint,
                    VertexStyle.SIZE_POINTED
                            / context.getTransform().getScale(),
                    OriPointColor.ORIPOINT_POINTED);
        }
    }

    protected void drawMousePoint(Graphics2D g2d,
            PaintScreenContext context) {
        if (context.getCurrentLogicalMousePoint() != null) {
            Point2D.Double point = context.getCurrentLogicalMousePoint();
            OriPointDrawer.drawPoint(
                    g2d,
                    point,
                    VertexStyle.SIZE_POINTED
                            / context.getTransform().getScale(),
                    OriPointColor.ORIPOINT_POINTED);
        }
    }

    protected void drawPointedLine(Graphics2D g2d,
            PaintScreenContext context) {
        PointedElement pointedElements = context.getPointedElements();
        OriLine oriLine = pointedElements.getOriLine();
        if (oriLine != null) {
            OriLineDrawer.drawLine(
                    g2d,
                    oriLine,
                    OriLineColor.POINTED,
                    LineStyle.STROKE_POINTED);
        }
    }

    protected void drawPointedFace(Graphics2D g2d,
            PaintScreenContext context) {
        PointedElement pointedElements = context.getPointedElements();
        OriFace face = pointedElements.getOriFace();
        if (face != null) {
            OriFaceDrawer.drawFace(g2d, face, OriFaceColor.ORI_FACE_POINTED);
        }
    }

    protected void drawPickedFaces(Graphics2D g2d,
            PaintScreenContext context) {
        PickedElements pickedElements = context.getPickedElements();
        for (OriFace face : pickedElements.getOriFaces()) {
            OriFaceDrawer.drawFace(g2d, face, OriFaceColor.ORI_FACE_PICKED);
        }
    }

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
