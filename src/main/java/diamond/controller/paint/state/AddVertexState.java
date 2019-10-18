/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.LineDivider;

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
        OriPoint pointedOriPoint = paintScreenContext
                .getPointedOriPoint();
        OriLine pointedOriLine = paintScreenContext.getPointedOriLine();
        if (pointedOriPoint != null && pointedOriLine != null) {
            LineDivider.divide(
                    pointedOriLine,
                    pointedOriPoint,
                    context.getPalette().getCP().getLines());
            return true;
        }
        return false;
    }

}
