/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.context.PaintContext;
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
    public void destroy(PaintContext context) {
        context.initialize();
    }

    @Override
    public void recover(PaintContext context) {
    }

    @Override
    public PaintActionInterface onLeftClick(PaintContext context) {
        doAction(context, context.getCurrentLogicalMousePoint());
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

        return context.getPointedOriPoint();
    }

    protected final void setCandidateVertexOnMove(PaintContext context) {
        context.setPointedOriPoint(NearestPointFinder.findAround(context));

    }

    protected final void setCandidateLineOnMove(PaintContext context) {
        context.setPointedOriLine(NearestLineFinder.findAround(context));
    }

    protected final void setCandidateFaceOnMove(PaintContext context) {
        if (context.getPointedOriFace() != null) {
            context.getPointedOriFace().isPointed = false;
        }
        context.setPointedOriFace(NearestFaceFinder.findAround(context));
        if (context.getPointedOriFace() != null) {
            context.getPointedOriFace().isPointed = true;
        }
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
            OriLineDrawer.drawLine(
                    g2d,
                    context.getPickedLines().get(i),
                    OriLineColor.PICKED,
                    LineStyle.STROKE_PICKED);
        }
    }

    protected void drawPickedPoints(Graphics2D g2d, PaintContext context) {
        for (int i = 0; i < context.getPickedPoints().size(); i++) {
            OriPoint point = context.getPickedPoints().get(i);
            OriPointDrawer.drawPoint(
                    g2d, point,
                    VertexStyle.SIZE_PICKED / context.getTransform().getScale(),
                    OriPointColor.ORIPOINT_PICKED);
        }
    }

    protected void drawPointedPoint(Graphics2D g2d,
            PaintContext context) {
        if (context.getPointedOriPoint() != null) {
            OriPoint candidate = context.getPointedOriPoint();
            OriPointDrawer.drawPoint(
                    g2d,
                    candidate,
                    VertexStyle.SIZE_POINTED / context.getTransform().getScale(),
                    OriPointColor.ORIPOINT_POINTED);
        }
    }

    protected void drawMousePoint(Graphics2D g2d,
            PaintContext context) {
        if (context.getCurrentLogicalMousePoint() != null) {
            Point2D.Double point = context.getCurrentLogicalMousePoint();
            OriPointDrawer.drawPoint(
                    g2d,
                    point,
                    VertexStyle.SIZE_POINTED / context.getTransform().getScale(),
                    OriPointColor.ORIPOINT_POINTED);
        }
    }

    protected void drawPointedLine(Graphics2D g2d,
            PaintContext context) {
        if (context.getPointedOriLine() != null) {
            OriLine candidate = context.getPointedOriLine();
            OriLineDrawer.drawLine(
                    g2d,
                    candidate,
                    OriLineColor.POINTED,
                    LineStyle.STROKE_POINTED);
        }
    }

    protected void drawPointedFace(Graphics2D g2d,
            PaintContext context) {
        OriFace face = context.getPointedOriFace();
        if (face != null) {
            OriFaceDrawer.drawFace(g2d, face, OriFaceColor.ORI_FACE_POINTED);
        }
    }

    protected void drawPickedFaces(Graphics2D g2d,
            PaintContext context) {
        for (OriFace face : context.getPickedOriFaces()) {
            OriFaceDrawer.drawFace(g2d, face, OriFaceColor.ORI_FACE_PICKED);
        }
    }

    protected void drawTemporaryLine(Graphics2D g2d, PaintContext context) {
        if (context.getPickedPoints().size() > 0) {
            OriPoint picked = context.getPickedPoints().peek();
            OriLineDrawer.drawLine(g2d,
                    new OriLine(new OriPoint(picked.x, picked.y),
                            context.getCandidateOriPoint(true),
                            LineType.CREASE),
                    OriLineColor.PICKED,
                    LineStyle.STROKE_TEMPORARY);
        }

    }

}
