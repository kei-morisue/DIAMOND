/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.arrow;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowScalingState extends OriLinePickkingState {

    @Override
    protected void initialize() {
        setPrevClass(ArrowScalingState.class);
        setNextClass(ArrowScalingState.class);
    }

    @Override
    protected void undoAction(Context context) {
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
    protected void onResult(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Double p = paintScreenContext.getCurrentLogicalMousePoint();
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
