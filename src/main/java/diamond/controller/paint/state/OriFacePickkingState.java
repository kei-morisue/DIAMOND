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
abstract public class OriFacePickkingState extends AbstractPaintState {

    @Override
    public PaintStateInterface doAction(PaintContext context,
            Double currentPoint) {
        if (!onAction(context, currentPoint)) {
            return this;
        }
        onResult(context);
        context.palette.getOriModel().fold();
        PaintStateInterface nextState = getNextState();
        return nextState;
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
