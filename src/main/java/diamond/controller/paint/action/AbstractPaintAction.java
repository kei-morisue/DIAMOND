/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

import diamond.controller.paint.PaintActionInterface;
import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.PaintStateInterface;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.NearestLineFinder;
import diamond.model.geom.util.NearestPointFinder;
import diamond.view.paint.EditMode;
import diamond.view.paint.screen.draw.OriDrawer;
import diamond.view.resource.color.ColorStyle;
import diamond.view.resource.graphic.LineStrokeSetting;
import diamond.view.resource.graphic.VertexSetting;

/**
 * @author long_
 *
 */
public abstract class AbstractPaintAction implements PaintActionInterface {
    private EditMode editMode = EditMode.INPUT;
    private boolean needSelect = false;
    private PaintStateInterface state;

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
            OriDrawer.drawLine(
                    g2d,
                    context.getpickedLines().get(i),
                    ColorStyle.ORILINE_PICKED,
                    LineStrokeSetting.STROKE_PICKED);
        }
    }

    private void drawPickedVertices(Graphics2D g2d, PaintContext context) {
        for (int i = 0; i < context.getPickedPoints().size(); i++) {
            Vector2d vertex = context.getPickedPoints().get(i);
            OriDrawer.drawPoint(
                    g2d, vertex,
                    VertexSetting.VERTEX_SIZE_PICKED,
                    ColorStyle.ORIPOINT_PICKED);
        }
    }

    protected void drawPointedVertex(Graphics2D g2d,
            PaintContext context) {
        if (context.pointedOriPoint != null) {
            Vector2d candidate = context.pointedOriPoint;
            OriDrawer.drawPoint(
                    g2d,
                    candidate,
                    VertexSetting.VERTEX_SIZE_POINTED,
                    ColorStyle.ORIPOINT_POINTED);
        }
    }

    protected void drawLine(Graphics2D g2d, Vector2d p0, Vector2d p1) {
        g2d.draw(new Line2D.Double(p0.x, p0.y,
                p1.x, p1.y));

    }

    protected void drawTemporaryLine(Graphics2D g2d, PaintContext context) {
        if (context.getPickedPoints().size() > 0) {
            Vector2d picked = context.getPickedPoints().peek();
            OriDrawer.drawLine(g2d,
                    new OriLine(new OriPoint(picked.x, picked.y),
                            context.getCandidateOriPoint(true),
                            LineType.AUX),
                    ColorStyle.ORILINE_CANDIDATE,
                    LineStrokeSetting.STROKE_MOVING);
        }

    }

}
