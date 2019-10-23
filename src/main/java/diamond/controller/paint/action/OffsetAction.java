/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.context.PointedElement;
import diamond.controller.paint.state.offset.OriPoint0PickkingState;
import diamond.model.geom.element.cp.OriPoint;
import diamond.view.screen.draw.RadarDrawer;

/**
 * @author long_
 *
 */
public class OffsetAction extends AbstractPaintAction {

    public OffsetAction() {
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        Stack<OriPoint> oriPoints = pickedElements.getOriPoints();
        if (oriPoints.size() > 0) {
            RadarDrawer.draw(g2d, paintScreenContext);
        } else {
            PointedElement pointedElements = paintScreenContext
                    .getPointedElements();
            pointedElements.draw(g2d);
        }
    }

}
