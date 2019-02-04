/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.OriPoint;
import diamond.model.geom.util.NearestPointFinder;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public abstract class OripointPickkingState extends AbstractPaintState {
    public OripointPickkingState() {
        super();
    }

    @Override
    protected void undoAction(PaintContext context) {
        if (context.getPickedPoints().size() > 0) {
            context.popLatestPickedPoint();
        }
    }

    @Override
    protected boolean onAction(
            PaintContext context,
            Double currentPoint) {
        OriPoint picked = NearestPointFinder.findAround(context);
        if (picked == null) {
            return false;
        }
        context.pushPoint(picked);
        return true;
    }

}
