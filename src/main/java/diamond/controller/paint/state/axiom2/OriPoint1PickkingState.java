/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom2;

import java.util.Set;
import java.util.Stack;

import diamond.Initials;
import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.LineClipper;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint1PickkingState.class);
        setNextClass(OriPoint1PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriPoint> pickedPoints = context.getPickedPoints();
        if (pickedPoints.size() != 2) {
            throw new RuntimeException();
        }
        OriPoint p0 = pickedPoints.get(0);
        OriPoint p1 = pickedPoints.get(1);

        OriLine line = createPerpendicularBisector(p0, p1,
                context.inputLineType, context.palette.getCP().getCutLines());
        context.palette.getCP().addLine(line);
        pickedPoints.clear();
    }

    private OriLine createPerpendicularBisector(
            OriPoint v0,
            OriPoint v1,
            LineType type,
            Set<OriLine> cutLines) {

        OriPoint centerPoint = v0.add(v1);
        centerPoint = centerPoint.scale(0.5);

        OriPoint dir = v0.sub(v1);
        double tmp = dir.y;
        dir.y = -dir.x;
        dir.x = tmp;
        dir.scale(Initials.PAPER_SIZE * 2);

        OriLine bisector = new OriLine(
                new OriPoint(centerPoint.x - dir.x, centerPoint.y - dir.y),
                new OriPoint(centerPoint.x + dir.x, centerPoint.y + dir.y),
                type);
        bisector = LineClipper.clipByCutLines(bisector, cutLines);
        return bisector;
    }
}
