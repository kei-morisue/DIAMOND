/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom3;

import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.BisectorUtil;

/**
 * @author long_
 *
 */
public class OriLine0PickkingState extends OriLinePickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        Stack<OriLine> pickedLines = pickedElements.getOriLines();
        Stack<OriPoint> pickedPoints = pickedElements.getOriPoints();
        if (pickedLines.size() != 1 || pickedPoints.size() != 3) {
            initialize();
            return;
        }
        OriPoint v0 = pickedPoints.get(0);
        OriPoint v1 = pickedPoints.get(1);
        OriPoint v2 = pickedPoints.get(2);
        OriLine l = pickedLines.get(0);
        OriLine line = BisectorUtil.getOriLine(v0, v1,
                v2, l, paintScreenContext.getInputLineType());
        context.getPalette().getCP().addLine(line);
        pickedPoints.clear();
        pickedLines.clear();
    }
}
