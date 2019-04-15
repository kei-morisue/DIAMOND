/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

import javax.vecmath.Vector3d;

import diamond.model.geom.element.Line;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class OriPointUtil {
    public static OriPoint mirroredPoint(
            OriPoint p, OriLine baseOriLine) {
        Line baseLine = new Line(baseOriLine);

        OriPoint dir0;
        if (isRightSide(p, baseLine)) {
            dir0 = new OriPoint(-baseLine.dir.y, baseLine.dir.x);
        } else {
            dir0 = new OriPoint(baseLine.dir.y, -baseLine.dir.x);
        }
        dir0.normalize();
        return p.add(dir0.scale(2.0 * DistanceUtil.Distance(p, baseLine)));
    }

    public static boolean isRightSide(Point2D.Double p, Line line) {
        Vector3d lineDir = new Vector3d(line.dir.x, line.dir.y, 0);
        Vector3d pointDir = new Vector3d(p.x - line.p.x, p.y - line.p.y, 0);
        Vector3d crossVec = new Vector3d();
        crossVec.cross(pointDir, lineDir);
        return crossVec.z > 0;
    }
}
