/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.util.NearestLineFinder;

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
        OriLine picked = NearestLineFinder.findAround(context);
        if (picked == null) {
            return false;
        }
        context.getPickedLines().push(picked);
        context.pointedOriLine = picked;
        return true;
    }
}
