/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector3d;

import diamond.model.geom.element.Line;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class MiscGeomUtil {
    public static boolean isRightSide(Vector2d p, Line line) {
        Vector3d lineDir = new Vector3d(line.dir.x, line.dir.y, 0);
        Vector3d pointDir = new Vector3d(p.x - line.p.x, p.y - line.p.y, 0);
        Vector3d crossVec = new Vector3d();
        crossVec.cross(pointDir, lineDir);
        return crossVec.z > 0;
    }

    //  Investigate the relationship between the point q with the segment p0, p1
    public static boolean CCWcheck(Vector2d p0, Vector2d p1, Vector2d q) {
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

    public static Vector2d getBisectorVec(Vector2d v0, Vector2d v1,
            Vector2d v2) {
        Vector2d v0_v1 = new Vector2d();
        v0_v1.sub(v0, v1);
        v0_v1.normalize();
        Vector2d v2_v1 = new Vector2d();
        v2_v1.sub(v2, v1);
        v2_v1.normalize();

        return new Vector2d(v0_v1.x + v2_v1.x, v0_v1.y + v2_v1.y);
    }

    public static Vector2d getSymmetricPoint(Vector2d p, Vector2d sp,
            Vector2d ep) {
        Vector2d cp = getNearestPointToLine(p, sp, ep);
        return new Vector2d(2 * cp.x - p.x, 2 * cp.y - p.y);
    }

    public static OriLine getLineByValue(Vector2d sv, double length,
            double deg_angle, LineType type) {
        Vector2d ev = new Vector2d(sv);
        double rad_angle = Math.toRadians(deg_angle);
        Vector2d dir = new Vector2d(length * Math.cos(rad_angle),
                length * Math.sin(rad_angle));
        ev.add(dir);
        return new OriLine(new OriPoint(sv.x, sv.y), new OriPoint(ev.x, ev.y),
                type);
    }

    private static Vector2d getNearestPointToLine(Vector2d p, Vector2d sp,
            Vector2d ep) {
        double x0 = sp.x;
        double y0 = sp.y;
        double x1 = ep.x;
        double y1 = ep.y;
        double px = p.x;
        double py = p.y;
        Vector2d sub0, sub, sub0b;

        sub0 = new Vector2d(x0 - px, y0 - py);
        sub0b = new Vector2d(-sub0.x, -sub0.y);
        sub = new Vector2d(x1 - x0, y1 - y0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        return new Vector2d(x0 + t * sub.x, y0 + t * sub.y);
    }
}
