/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offsetarrow;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.OffsetState;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowOffsetState extends OffsetState {

    @Override
    protected void initialize() {
        setPrevClass(Arrow0PickkingState.class);
        setNextClass(Arrow0PickkingState.class);
    }

    @Override
    protected void undoAction(Context context) {
        Set<OriLine> lines = context.getPalette().getCP().getLines();
        for (OriLine line : lines) {
            AbstractArrow arrow = line.getArrow();
            if (arrow != null) {
                if (arrow.isSelected()) {
                    arrow.setOffset(new Double(.0, .0));
                }
            }
        }
    }

    @Override
    protected void onResult(Context context) {
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
    public void setCandate(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Double p = paintScreenContext.getCurrentLogicalMousePoint();
        if (p != null) {
            Cp cp = context.getPalette().getCP();
            for (OriLine line : cp.getLines()) {
                AbstractArrow arrow = line.getArrow();
                if (arrow != null) {
                    if (arrow.isSelected()) {
                        Double rotated = getRotatedPoint(context, p);
                        arrow.setOffset(rotated);
                    }
                }
            }
        }
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        return true;
    }

}
