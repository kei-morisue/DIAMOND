/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import diamond.controller.paint.state.PaintStateInterface;
import diamond.model.geom.element.OriLine;
import diamond.model.geom.util.NearestLineFinder;
import diamond.model.geom.util.NearestPointFinder;
import diamond.model.palette.cp.LineSetting;
import diamond.model.palette.cp.VertexSetting;
import diamond.view.paint.EditMode;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public abstract class AbstractPaintAction implements PaintActionInterface {
    private EditMode editMode = EditMode.INPUT;
    private boolean needSelect = false;
    private PaintStateInterface state;

    protected void log(Vector2d p) {
        System.out.println(p.toString());
    }

    protected final void setActionState(PaintStateInterface state) {
        this.state = state;
    }

    protected final PaintStateInterface getActionState() {
        return state;
    }

    @Override
    public final boolean needSelect() {
        return needSelect;
    }

    protected final void setNeedSelect(boolean selectable) {
        this.needSelect = selectable;
    }

    protected final void setEditMode(EditMode mode) {
        editMode = mode;
    }

    @Override
    public final EditMode getEditMode() {
        return editMode;
    }

    @Override
    public void destroy(PaintContext context) {
        context.initialize();
    }

    @Override
    public void recover(PaintContext context) {
    }

    @Override
    public PaintActionInterface onLeftClick(
            PaintContext context,
            AffineTransform affine) {
        doAction(context, context.currentLogicalMousePoint);
        return this;
    }

    @Override
    public PaintActionInterface onRightClick(PaintContext context,
            AffineTransform affine) {
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
    public Vector2d onMove(PaintContext context) {
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

    @Override
    public abstract void onPress(PaintContext context);

    @Override
    public abstract void onDrag(PaintContext context);

    @Override
    public abstract void onRelease(PaintContext context);

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedLines(g2d, context);
        drawPickedVertices(g2d, context);
    }

    private void drawPickedLines(Graphics2D g2d, PaintContext context) {
        for (int i = 0; i < context.getpickedLines().size(); i++) {
            g2d.setColor(LineSetting.LINE_COLOR_PICKED);
            g2d.setStroke(LineSetting.STROKE_PICKED);
            drawLine(g2d, context.getpickedLines().get(i));
        }
    }

    private void drawPickedVertices(Graphics2D g2d, PaintContext context) {
        for (int i = 0; i < context.getPickedPoints().size(); i++) {
            g2d.setColor(VertexSetting.VERTEX_COLOR_PICKED);
            Vector2d vertex = context.getPickedPoints().get(i);
            drawVertex(g2d, context, vertex.x, vertex.y,
                    VertexSetting.VERTEX_SIZE_PICKED);
        }
    }

    /**
     * draw a picked vertex as an small rectangle at (x, y)
     * @param g2d
     * @param context
     * @param x
     * @param y
     */
    protected void drawVertex(Graphics2D g2d, PaintContext context,
            double x, double y, double size) {
        double scale = context.getScale();
        g2d.fill(new Rectangle2D.Double(x - 5.0 / scale,
                y - 5.0 / scale, 10.0 / scale, 10.0 / scale));

    }

    protected void drawPickCandidateVertex(Graphics2D g2d,
            PaintContext context) {
        if (context.pointedOriPoint != null) {
            g2d.setColor(VertexSetting.VERTEX_COLOR_POINTED);
            Vector2d candidate = context.pointedOriPoint;
            drawVertex(g2d, context, candidate.x, candidate.y,
                    VertexSetting.VERTEX_SIZE_POINTED);
        }
    }

    protected void drawLine(Graphics2D g2d, OriLine line) {
        g2d.draw(new Line2D.Double(line.p0.x, line.p0.y,
                line.p1.x, line.p1.y));

    }

    protected void drawLine(Graphics2D g2d, Vector2d p0, Vector2d p1) {
        g2d.draw(new Line2D.Double(p0.x, p0.y,
                p1.x, p1.y));

    }

    protected void drawPickCandidateLine(Graphics2D g2d, PaintContext context) {
        if (context.pointedOriLine != null) {
            g2d.setColor(LineSetting.LINE_COLOR_CANDIDATE);
            OriLine candidate = context.pointedOriLine;
            drawLine(g2d, candidate);
        }
    }

    protected void drawTemporaryLine(Graphics2D g2d, PaintContext context) {
        if (context.getPickedPoints().size() > 0) {
            Vector2d picked = context.getPickedPoints().peek();
            g2d.setColor(LineSetting.LINE_COLOR_CANDIDATE);
            drawLine(g2d, picked,
                    context.getCandidateOriPoint(true));
        }

    }

}
