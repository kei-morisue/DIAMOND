/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public abstract class OriLinePickkingState extends AbstractPaintState {
    @Override
    protected void undoAction(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        if (paintScreenContext.getPickedLines().size() > 0) {
            paintScreenContext.popLatestPickedPoint();
        }
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine pointedOriLine = paintScreenContext.getPointedOriLine();
        if (pointedOriLine != null) {
            Stack<OriLine> pickedLines = paintScreenContext.getPickedLines();
            if (pickedLines.contains(pointedOriLine)) {
                pickedLines.remove(pointedOriLine);
            } else {
                pickedLines.push(pointedOriLine);
            }
            return true;
        }
        return false;
    }
}
