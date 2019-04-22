/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.PaintStateInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.util.NearestFaceFinder;
import diamond.model.geom.util.NearestLineFinder;
import diamond.model.geom.util.NearestPointFinder;
import diamond.view.screen.draw.OriDrawer;
import diamond.view.screen.draw.style.ColorStyle;
import diamond.view.screen.draw.style.LineStrokeStyle;
import diamond.view.screen.draw.style.VertexStyle;

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
    public void destroy(PaintContext context) {
        context.initialize();
    }

    @Override
    public void recover(PaintContext context) {
    }

    @Override
    public PaintActionInterface onLeftClick(PaintContext context) {
        doAction(context, context.currentLogicalMousePoint);
        return this;
    }

    @Override
    public PaintActionInterface onRightClick(PaintContext context) {
        undo(context);
        return this;
    }

    @Override
    public void doAction(
            PaintContext context,
            Point2D.Double clickedPoint) {
        state = state.doAction(context, clickedPoint);
    }

    @Override
    public void undo(PaintContext paintContext) {
        state = state.undo(paintContext);
    }

    @Override
    public Point2D.Double onMove(PaintContext context) {
        setCandidateVertexOnMove(context);
        setCandidateLineOnMove(context);

        return context.pointedOriPoint;
    }

    protected final void setCandidateVertexOnMove(PaintContext context) {
        context.pointedOriPoint = NearestPointFinder.findAround(context);

    }

    protected final void setCandidateLineOnMove(PaintContext context) {
        context.pointedOriLine = NearestLineFinder.findAround(context);
    }

    protected final void setCandidateFaceOnMove(PaintContext context) {
        context.pointedOriFace = NearestFaceFinder.findAround(context);
    }

    @Override
    public void onPress(PaintContext context) {
    };

    @Override
    public void onDrag(PaintContext context) {
    };

    @Override
    public void onRelease(PaintContext context) {
    };

    protected void drawPickedLines(Graphics2D g2d, PaintContext context) {
        for (int i = 0; i < context.getPickedLines().size(); i++) {
            OriDrawer.drawLine(
                    g2d,
                    context.getPickedLines().get(i),
                    ColorStyle.ORILINE_PICKED,
                    LineStrokeStyle.STROKE_PICKED);
        }
    }

    protected void drawPickedPoints(Graphics2D g2d, PaintContext context) {
        for (int i = 0; i < context.getPickedPoints().size(); i++) {
            OriPoint point = context.getPickedPoints().get(i);
            OriDrawer.drawPoint(
                    g2d, point,
                    VertexStyle.SIZE_PICKED / context.transform.getScale(),
                    ColorStyle.ORIPOINT_PICKED);
        }
    }

    protected void drawPointedPoint(Graphics2D g2d,
            PaintContext context) {
        if (context.pointedOriPoint != null) {
            OriPoint candidate = context.pointedOriPoint;
            OriDrawer.drawPoint(
                    g2d,
                    candidate,
                    VertexStyle.SIZE_POINTED / context.transform.getScale(),
                    ColorStyle.ORIPOINT_POINTED);
        }
    }

    protected void drawMousePoint(Graphics2D g2d,
            PaintContext context) {
        if (context.currentLogicalMousePoint != null) {
            Point2D.Double point = context.currentLogicalMousePoint;
            OriDrawer.drawPoint(
                    g2d,
                    point,
                    VertexStyle.SIZE_POINTED / context.transform.getScale(),
                    ColorStyle.ORIPOINT_POINTED);
        }
    }

    protected void drawPointedLine(Graphics2D g2d,
            PaintContext context) {
        if (context.pointedOriLine != null) {
            OriLine candidate = context.pointedOriLine;
            OriDrawer.drawLine(
                    g2d,
                    candidate,
                    ColorStyle.ORILINE_POINTED,
                    LineStrokeStyle.STROKE_POINTED);
        }
    }

    protected void drawPointedFace(Graphics2D g2d,
            PaintContext context) {
        OriFace face = context.pointedOriFace;
        if (face != null) {
            OriDrawer.drawFace(g2d, face.getOutline(),
                    ColorStyle.ORI_FACE_POINTED);
        }
    }

    protected void drawTemporaryLine(Graphics2D g2d, PaintContext context) {
        if (context.getPickedPoints().size() > 0) {
            OriPoint picked = context.getPickedPoints().peek();
            OriDrawer.drawLine(g2d,
                    new OriLine(new OriPoint(picked.x, picked.y),
                            context.getCandidateOriPoint(true),
                            LineType.AUX),
                    ColorStyle.ORILINE_PICKED,
                    LineStrokeStyle.STROKE_MOVING);
        }

    }

}
