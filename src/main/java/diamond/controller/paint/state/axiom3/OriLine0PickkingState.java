/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom3;

import java.util.Stack;

import javax.vecmath.Vector2d;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.Line;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.CrossPointUtil;
import diamond.model.geom.util.GeomUtil;

/**
 * @author long_
 *
 */
public class OriLine0PickkingState extends OriLinePickkingState {

    public OriLine0PickkingState() {
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
        Stack<OriLine> pickedLines = context.getPickedLines();
        Stack<OriPoint> pickedPoints = context.getPickedPoints();
        if (pickedLines.size() != 1 || pickedPoints.size() != 3) {
            throw new RuntimeException();
        }
        Vector2d v0 = pickedPoints.get(0);
        Vector2d v1 = pickedPoints.get(1);
        Vector2d v2 = pickedPoints.get(2);
        OriLine l = pickedLines.get(0);
        OriLine line = createAngleBisectorLine(v0, v1,
                v2, l, context.inputLineType);
        context.getCP().addLine(line);
        pickedPoints.clear();
        pickedLines.clear();
    }

    public OriLine createAngleBisectorLine(
            Vector2d v0, Vector2d v1, Vector2d v2,
            OriLine l,
            LineType type) {

        Vector2d dir = GeomUtil.getBisectorVec(v0, v1, v2);
        Vector2d cp = CrossPointUtil.getCrossPoint(
                new Line(l.p0, new Vector2d(l.p1.x - l.p0.x, l.p1.y - l.p0.y)),
                new Line(v1, dir));

        OriLine bisector = new OriLine(v1, cp, type);
        return bisector;

    }
}
