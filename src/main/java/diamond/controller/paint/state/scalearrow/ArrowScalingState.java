/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.scalearrow;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class ArrowScalingState extends OriPointPickkingState {

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
                    arrow.setScale(1.0);
                }
            }
        }
        onResult(context);
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
        super.setCandate(context);
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Double p = paintScreenContext.getCurrentLogicalMousePoint();
        if (p != null) {
            Cp cp = context.getPalette().getCP();
            for (OriLine line : cp.getLines()) {
                AbstractArrow arrow = line.getArrow();
                if (arrow != null) {
                    if (arrow.isSelected()) {
                        arrow.setScale(Point2DUtil.norm(p) * 0.01);
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
