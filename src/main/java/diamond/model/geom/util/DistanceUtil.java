/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.Line;

/**
 * @author long_
 *
 */
public class DistanceUtil {
    public static double Distance(Vector2d p0, Vector2d p1) {
        return Distance(p0.x, p0.y, p1.x, p1.y);
    }

    public static double DistanceSquared(Vector2d p0, Vector2d p1) {
        return DistanceSquared(p0.x, p0.y, p1.x, p1.y);
    }

    public static double DistanceSquared(double x0, double y0, double x1,
            double y1) {
        return (x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1);
    }

    public static Vector2d getIncenter(Vector2d v0, Vector2d v1, Vector2d v2) {
        double l0 = Distance(v1, v2);
        double l1 = Distance(v0, v2);
        double l2 = Distance(v0, v1);

        Vector2d vc = new Vector2d();
        vc.x = (v0.x * l0 + v1.x * l1 + v2.x * l2) / (l0 + l1 + l2);
        vc.y = (v0.y * l0 + v1.y * l1 + v2.y * l2) / (l0 + l1 + l2);

        return vc;
    }

    public static double DistancePointToSegment(Vector2d p, Vector2d sp,
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

        if (t < 0.0) {
            return Distance(px, py, x0, y0);
        } else if (t > 1.0) {
            return Distance(px, py, x1, y1);
        } else {
            return Distance(x0 + t * sub.x, y0 + t * sub.y, px, py);
        }

    }

    public static double DistancePointToSegment(Vector2d p, Vector2d sp,
            Vector2d ep, Vector2d nearestPoint) {
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

        if (t < 0.0) {
            nearestPoint.set(sp);
            return Distance(px, py, x0, y0);
        } else if (t > 1.0) {
            nearestPoint.set(ep);
            return Distance(px, py, x1, y1);
        } else {
            nearestPoint.set(x0 + t * sub.x, y0 + t * sub.y);
            return Distance(x0 + t * sub.x, y0 + t * sub.y, px, py);
        }

    }

    public static double DistancePointToLine(Vector2d p, Line line) {
        double x0 = line.p.x;
        double y0 = line.p.y;
        double x1 = line.p.x + line.dir.x;
        double y1 = line.p.y + line.dir.y;
        double px = p.x;
        double py = p.y;
        Vector2d sub0, sub, sub0b;

        sub0 = new Vector2d(x0 - px, y0 - py);
        sub0b = new Vector2d(-sub0.x, -sub0.y);
        sub = new Vector2d(x1 - x0, y1 - y0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        return Distance(x0 + t * sub.x, y0 + t * sub.y, px, py);

    }

    public static double Distance(Vector2d p, Line line, double[] param) {
        Vector2d sub0, sub, sub0b;
        double x0 = line.p.x;
        double y0 = line.p.y;
        double x1 = line.p.x + line.dir.x;
        double y1 = line.p.y + line.dir.y;
        double px = p.x;
        double py = p.y;

        sub0 = new Vector2d(x0 - px, y0 - py);
        sub0b = new Vector2d(-sub0.x, -sub0.y);
        sub = new Vector2d(x1 - x0, y1 - y0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        param[0] = t;
        return Distance(x0 + t * sub.x, y0 + t * sub.y, px, py);
    }

    public static double Distance(Vector2d p, Line line) {
        Vector2d sub0, sub, sub0b;
        double x0 = line.p.x;
        double y0 = line.p.y;
        double x1 = line.p.x + line.dir.x;
        double y1 = line.p.y + line.dir.y;
        double px = p.x;
        double py = p.y;

        sub0 = new Vector2d(x0 - px, y0 - py);
        sub0b = new Vector2d(-sub0.x, -sub0.y);
        sub = new Vector2d(x1 - x0, y1 - y0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        return Distance(x0 + t * sub.x, y0 + t * sub.y, px, py);
    }

    private static double Distance(double x0, double y0, double x1, double y1) {
        return Math.sqrt((x0 - x1) * (x0 - x1) + (y0 - y1) * (y0 - y1));
    }

}
