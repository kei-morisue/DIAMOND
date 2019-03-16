/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

import javax.vecmath.Vector3d;

import diamond.model.geom.element.Line;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class GeomUtil {
    public static boolean isRightSide(Point2D.Double p, Line line) {
        Vector3d lineDir = new Vector3d(line.dir.x, line.dir.y, 0);
        Vector3d pointDir = new Vector3d(p.x - line.p.x, p.y - line.p.y, 0);
        Vector3d crossVec = new Vector3d();
        crossVec.cross(pointDir, lineDir);
        return crossVec.z > 0;
    }

    //  Investigate the relationship between the point q with the segment p0, p1
    public static boolean CCWcheck(Point2D.Double p0, Point2D.Double p1,
            Point2D.Double q) {
        double dx1, dx2, dy1, dy2;

        dx1 = p1.x - p0.x;
        dy1 = p1.y - p0.y;
        dx2 = q.x - p0.x;
        dy2 = q.y - p0.y;

        if (dx1 * dy2 > dy1 * dx2) {
            return true;
        }
        return false;
    }

    public static OriPoint getBisectorVec(OriPoint v0, OriPoint v1,
            OriPoint v2) {
        OriPoint v0_v1 = v0.sub(v1);
        v0_v1.normalize();
        OriPoint v2_v1 = v2.sub(v1);
        v2_v1.normalize();

        return new OriPoint(v0_v1.x + v2_v1.x, v0_v1.y + v2_v1.y);
    }

    public static OriLine getLineByValue(OriPoint sv, double length,
            double deg_angle, LineType type) {
        OriPoint ev = new OriPoint(sv);
        double rad_angle = Math.toRadians(deg_angle);
        OriPoint dir = new OriPoint(length * Math.cos(rad_angle),
                length * Math.sin(rad_angle));
        ev.add(dir);
        return new OriLine(new OriPoint(sv.x, sv.y), new OriPoint(ev.x, ev.y),
                type);
    }

}
