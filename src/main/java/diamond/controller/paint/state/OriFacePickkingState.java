/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
abstract public class OriFacePickkingState extends AbstractPaintState {

    @Override
    protected void undoAction(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getOriModel().fold();

    }

    @Override
    protected void onResult(PaintContext context) {
        if (context.pointedOriFace != null) {
            Stack<OriFace> pickedOriFaces = context.getPickedOriFaces();
            pickedOriFaces.clear();
            pickedOriFaces.push(context.pointedOriFace);
        }

    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return true;
    }

}
