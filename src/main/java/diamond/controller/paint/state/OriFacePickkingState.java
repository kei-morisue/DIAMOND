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
public class OriFacePickkingState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setNextClass(OriFacePickkingState.class);
        setPrevClass(OriFacePickkingState.class);

    }

    @Override
    protected void undoAction(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
        if (context.pointedOriFace != null) {
            context.getPickedOriFaces().push(context.pointedOriFace);
        }
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return true;
    }

}
