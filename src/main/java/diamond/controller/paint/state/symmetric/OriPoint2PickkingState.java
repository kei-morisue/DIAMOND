/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.symmetric;

import java.util.Collection;
import java.util.Stack;

import javax.vecmath.Vector2d;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.SymmetricLineFactory;

/**
 * @author long_
 *
 */
public class OriPoint2PickkingState extends OriPointPickkingState {

    public OriPoint2PickkingState() {
        super();
        initialize();
    }

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriPoint> pickedPoints = context.getPickedPoints();
        if (pickedPoints.size() != 3) {
            throw new RuntimeException();
        }
        Collection<OriLine> creasePattern = context.getCP().getLines();

        Vector2d first = pickedPoints.get(0);
        Vector2d second = pickedPoints.get(1);
        Vector2d third = pickedPoints.get(2);

        SymmetricLineFactory symmetricLineFactory = new SymmetricLineFactory();
        Collection<OriLine> lines = symmetricLineFactory
                .createSymmetricLineAutoWalk(
                        first,
                        second,
                        third,
                        first,
                        creasePattern, context.inputLineType);
        context.getCP().addAll(lines);
        pickedPoints.clear();
    }

}
