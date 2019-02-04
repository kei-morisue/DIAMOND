/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.pick;

import java.util.Stack;

import diamond.controller.paint.state.OripointPickkingState;
import diamond.model.geom.element.OriLine;
import diamond.model.geom.element.OriPoint;
import diamond.model.palette.CreasePatternHolder;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OripointPickkingState {

    @Override
    protected void initialize() {
        setNextClass(Oripoint0PickkingState.class);
        setPrevClass(Oripoint0PickkingState.class);

    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriPoint> pickedPoint = context.getPickedPoints();
        if (pickedPoint.size() != 2) {
            throw new RuntimeException();
        }
        OriLine line = new OriLine(
                pickedPoint.get(0),
                pickedPoint.get(1),
                context.inputLineType);
        CreasePatternHolder.getCP().addLine(line);
        pickedPoint.clear();
    }

}
