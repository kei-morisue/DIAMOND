/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowScalingState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(ArrowScalingState.class);
        setNextClass(ArrowScalingState.class);
    }

    @Override
    protected void undoAction(PaintContext context) {
        Set<OriLine> lines = context.getPalette().getCP().getLines();
        for (OriLine line : lines) {
            AbstractArrow arrow = line.getArrow();
            if (arrow != null) {
                if (arrow.isSelected()) {
                    arrow.setScale(1.0);
                }
            }
        }
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        Double p = context.getCurrentLogicalMousePoint();
        if (p != null) {
            Cp cp = context.getPalette().getCP();
            for (OriLine line : cp.getLines()) {
                AbstractArrow arrow = line.getArrow();
                if (arrow != null) {
                    if (arrow.isSelected()) {
                        arrow.setScale(p.distance(0, 0) * 0.01);
                    }
                }
            }
            return true;
        }
        return false;
    }

}
