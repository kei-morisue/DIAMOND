/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.context.PointedElement;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.util.NearestFaceFinder;

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
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        if (pointedElements.getOriFace() != null) {
            PickedElements pickedElements = paintScreenContext
                    .getPickedElements();
            Stack<OriFace> pickedOriFaces = pickedElements.getOriFaces();
            pickedOriFaces.push(pointedElements.getOriFace());
            return true;
        }
        return false;
    }

    @Override
    public void setCandate(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriFace oriFace = pointedElements.getOriFace();
        if (oriFace != null) {
            oriFace.isPointed = false;
        }
        OriFace findAround = NearestFaceFinder.findAround(context);
        paintScreenContext.getPointedElements().setOriFace(findAround);
        if (oriFace != null) {
            oriFace.isPointed = true;
        }
    }
}
