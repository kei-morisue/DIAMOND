/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class AnyPointPickkingState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setNextClass(AnyPointPickkingState.class);
        setPrevClass(AnyPointPickkingState.class);

    }

    @Override
    protected void undoAction(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
        context.palette.getCP().getOriModel()
                .setOriginPoint(context.currentLogicalMousePoint);
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return true;
    }

}
