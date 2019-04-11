/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.util.Set;

import diamond.Initials;
import diamond.model.geom.element.Line;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.palette.cp.editor.LineClipper;

/**
 * @author long_
 *
 */
public class BisectorUtil {

    public static Line getLine(OriPoint v0, OriPoint v1,
            OriPoint v2) {
        OriPoint v0_v1 = v0.sub(v1);
        v0_v1.normalize();
        OriPoint v2_v1 = v2.sub(v1);
        v2_v1.normalize();
        OriPoint oriPoint = v0_v1.add(v2_v1);
        return new Line(v1, oriPoint);
    }

    public static OriLine getPerpendicularOriLine(
            OriPoint v0,
            OriPoint v1,
            LineType type,
            Set<OriLine> cutLines) {

        OriPoint centerPoint = v0.add(v1).scale(0.5);

        OriPoint dir = v0.sub(v1);
        double tmp = dir.y;
        dir.y = -dir.x;
        dir.x = tmp;
        dir = dir.scale(Initials.PAPER_SIZE * 5);

        OriLine bisector = new OriLine(
                centerPoint.sub(dir),
                centerPoint.add(dir),
                type);
        bisector = LineClipper.clip(bisector, cutLines);
        return bisector;
    }

    public static OriLine getOriLine(
            OriPoint v0, OriPoint v1, OriPoint v2,
            OriLine l,
            LineType type) {
        OriPoint cp = CrossPointUtil.getCrossPoint(
                new Line(l),
                getLine(v0, v1, v2));
        return new OriLine(v1, cp, type);

    }
}
