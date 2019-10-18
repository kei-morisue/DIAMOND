/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom2;

import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
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
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Stack<OriPoint> pickedPoints = paintScreenContext.getPickedPoints();
        if (pickedPoints.size() != 2) {
            throw new RuntimeException();
        }
        OriPoint p0 = pickedPoints.get(0);
        OriPoint p1 = pickedPoints.get(1);

        OriLine line = BisectorUtil.getPerpendicularOriLine(p0, p1,
                paintScreenContext.getInputLineType(),
                context.getPalette().getCP().getCutLines());
        context.getPalette().getCP().addLine(line);
        pickedPoints.clear();
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();
    }

}
