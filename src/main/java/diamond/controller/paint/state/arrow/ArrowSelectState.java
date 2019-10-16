/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowSelectState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(ArrowSelectState.class);
        setNextClass(ArrowSelectState.class);
    }

    @Override
    protected void undoAction(PaintContext context) {
        Set<OriLine> lines = context.palette.getCP().getLines();
        for (OriLine line : lines) {
            AbstractArrow arrow = line.getArrow();
            if (arrow != null) {
                arrow.unselect();
            }
        }
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriLine line = context.getPointedOriLine();
        if (line != null) {
            AbstractArrow arrow = line.getArrow();
            if (arrow != null) {
                arrow.selectUnselect();
            }
            return true;
        }
        return false;
    }

}
