/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom1;

import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setNextClass(OriPoint0PickkingState.class);
        setPrevClass(OriPoint0PickkingState.class);

    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        Stack<OriPoint> pickedPoint = paintScreenContext.getPickedPoints();
        if (pickedPoint.size() != 2) {
            throw new RuntimeException();
        }
        OriLine line = new OriLine(
                pickedPoint.get(0),
                pickedPoint.get(1),
                paintScreenContext.getInputLineType());
        context.getPalette().getCP().addLine(line);
        pickedPoint.clear();
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();
    }

}
