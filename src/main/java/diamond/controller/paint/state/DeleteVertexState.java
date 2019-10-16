/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.PaintContext;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.LineRemover;

/**
 * @author long_
 *
 */
public class DeleteVertexState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(DeleteVertexState.class);
        setNextClass(DeleteVertexState.class);

    }

    @Override
    protected void undoAction(PaintContext context) {

    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getCP().rebuildModel();

    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriPoint pointedOriPoint = context.getPointedOriPoint();
        if (pointedOriPoint != null) {
            LineRemover.merge2LinesAt(
                    pointedOriPoint,
                    context.palette.getCP().getLines());
            return true;
        }
        return false;
    }

}
