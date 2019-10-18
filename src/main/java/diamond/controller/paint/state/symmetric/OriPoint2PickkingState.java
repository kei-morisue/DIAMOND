/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.symmetric;

import java.util.Collection;
import java.util.Stack;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.SymmetricLineFactory;

/**
 * @author long_
 *
 */
public class OriPoint2PickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        Stack<OriPoint> pickedPoints = pickedElements.getOriPoints();
        if (pickedPoints.size() != 3) {
            throw new RuntimeException();
        }
        Collection<OriLine> creasePattern = context.getPalette().getCP()
                .getLines();

        OriPoint first = pickedPoints.get(0);
        OriPoint second = pickedPoints.get(1);
        OriPoint third = pickedPoints.get(2);

        SymmetricLineFactory symmetricLineFactory = new SymmetricLineFactory();
        Collection<OriLine> lines = symmetricLineFactory
                .createSymmetricLineAutoWalk(
                        first,
                        second,
                        third,
                        first,
                        creasePattern, paintScreenContext.getInputLineType());
        context.getPalette().getCP().addAll(lines);
        pickedPoints.clear();
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();
    }
}
