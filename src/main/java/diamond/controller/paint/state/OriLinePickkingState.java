/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.paint.context.PaintContext;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public abstract class OriLinePickkingState extends AbstractPaintState {
    @Override
    protected void undoAction(PaintContext context) {
        if (context.getPickedLines().size() > 0) {
            context.popLatestPickedPoint();
        }
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getCP().rebuildModel();
    }

    @Override
    protected boolean onAction(
            PaintContext context,
            Double currentPoint) {
        OriLine pointedOriLine = context.getPointedOriLine();
        if (pointedOriLine != null) {
            Stack<OriLine> pickedLines = context.getPickedLines();
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
