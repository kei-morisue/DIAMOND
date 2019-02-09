/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom2;

import java.util.Set;
import java.util.Stack;

import javax.vecmath.Vector2d;

import diamond.Initials;
import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OripointPickkingState;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.LineClipper;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OripointPickkingState {
    public OriPoint1PickkingState() {
        super();
        initialize();
    }

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
        Vector2d p0 = pickedPoints.get(0);
        Vector2d p1 = pickedPoints.get(1);

        OriLine line = createPerpendicularBisector(p0, p1,
                context.inputLineType, context.getCP().getCutLines());
        context.getCP().addLine(line);
        pickedPoints.clear();
    }

    private OriLine createPerpendicularBisector(
            Vector2d v0,
            Vector2d v1,
            LineType type,
            Set<OriLine> cutLines) {

        Vector2d centerPoint = (new Vector2d(v0));
        centerPoint.add(v1);
        centerPoint.scale(0.5);

        Vector2d dir = new Vector2d();
        dir.sub(v0, v1);
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
