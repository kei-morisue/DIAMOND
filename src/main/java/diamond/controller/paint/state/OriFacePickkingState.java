/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
abstract public class OriFacePickkingState extends AbstractPaintState {

    @Override
    protected void undoAction(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getOriModel().fold();

    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        if (paintScreenContext.getPointedOriFace() != null) {
            Stack<OriFace> pickedOriFaces = paintScreenContext
                    .getPickedOriFaces();
            pickedOriFaces.push(paintScreenContext.getPointedOriFace());
            return true;
        }
        return false;
    }

}
