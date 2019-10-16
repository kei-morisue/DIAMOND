/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom2;

import java.util.Stack;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.BisectorUtil;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint1PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriPoint> pickedPoints = context.getPickedPoints();
        if (pickedPoints.size() != 2) {
            throw new RuntimeException();
        }
        OriPoint p0 = pickedPoints.get(0);
        OriPoint p1 = pickedPoints.get(1);

        OriLine line = BisectorUtil.getPerpendicularOriLine(p0, p1,
                context.getInputLineType(), context.palette.getCP().getCutLines());
        context.palette.getCP().addLine(line);
        pickedPoints.clear();
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getCP().rebuildModel();
    }

}
