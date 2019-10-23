/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public abstract class ArrowPickkingState extends OriLinePickkingState {

    @Override
    protected void undoAction(Context context) {
        Set<OriLine> lines = context.getPalette().getCP().getLines();
        for (OriLine line : lines) {
            AbstractArrow arrow = line.getArrow();
            if (arrow != null) {
                arrow.unselect();
            }
        }
    }

    @Override
    protected void rebuild(Context context) {
    }

    @Override
    protected void onResult(Context context) {
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine line = paintScreenContext.getPointedElements().getOriLine();
        if (line != null) {
            AbstractArrow arrow = line.getArrow();
            if (arrow != null) {
                arrow.selectUnselect();
                if (arrow.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

}
