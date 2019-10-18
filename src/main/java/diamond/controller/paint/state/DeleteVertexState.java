/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PointedElement;
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
    protected void undoAction(Context context) {

    }

    @Override
    protected void onResult(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();

    }

    @Override
    protected boolean onAction(Context context,
            Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriPoint pointedOriPoint = pointedElements.getOriPoint();
        if (pointedOriPoint != null) {
            LineRemover.merge2LinesAt(
                    pointedOriPoint,
                    context.getPalette().getCP().getLines());
            return true;
        }
        return false;
    }

}
