/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.context.PointedElement;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.NearestPointFinder;

/**
 * @author long_
 *
 */
public abstract class OriPointPickkingState extends AbstractPaintState {

    @Override
    protected void undoAction(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        if (pickedElements.getOriPoints().size() > 0) {
            pickedElements.popOriPoint();
        }
    }

    @Override
    protected boolean onAction(
            Context context,
            Double currentPoint) {
        OriPoint picked = NearestPointFinder.findAround(context);
        if (picked == null) {
            return false;
        }
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        pickedElements.pushOriPoint(picked);
        pointedElements.setOriPoint(picked);
        return true;
    }

}
