/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.LineDivider;

/**
 * @author long_
 *
 */
public class AddVertexState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(AddVertexState.class);
        setNextClass(AddVertexState.class);

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
        OriPoint pointedOriPoint = context.pointedOriPoint;
        OriLine pointedOriLine = context.pointedOriLine;
        if (pointedOriPoint != null && pointedOriLine != null) {
            LineDivider.divide(
                    pointedOriLine,
                    pointedOriPoint,
                    context.palette.getCP().getLines());
            return true;
        }
        return false;
    }

}
