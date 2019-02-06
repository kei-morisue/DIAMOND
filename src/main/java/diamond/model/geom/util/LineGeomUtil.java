/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class LineGeomUtil {
    // Returns false if nothing is in the clip area
    public static boolean clipLine(OriLine l, double halfWidth) {
        Vector2d p = new Vector2d(l.p0);
        Vector2d dir = new Vector2d();
        dir.sub(l.p1, l.p0);

        // If horizontal
        if (Math.abs(dir.y) < Constants.EPS) {
            if (p.y < -halfWidth || p.y > halfWidth) {
                return false;
            }

            l.p0.set(-halfWidth, p.y);
            l.p1.set(halfWidth, p.y);
            return true;
        }
        // If vertical
        if (Math.abs(dir.x) < Constants.EPS) {
            if (p.x < -halfWidth || p.x > halfWidth) {
                return false;
            }

            l.p0.set(p.x, -halfWidth);
            l.p1.set(p.x, halfWidth);
            return true;
        }

        // If you do not have any horizontal vertical
        // Cut down
        {
            double up_t = (halfWidth - p.y) / dir.y;
            double up_x = p.x + up_t * dir.x;

            if (up_x < -halfWidth) {
                double left_t = (-halfWidth - p.x) / dir.x;
                double left_y = p.y + left_t * dir.y;
                if (left_y < -halfWidth) {
                    return false;
                }
                l.p0.set(-halfWidth, left_y);
            } else if (up_x > halfWidth) {
                double right_t = (halfWidth - p.x) / dir.x;
                double right_y = p.y + right_t * dir.y;
                if (right_y < -halfWidth) {
                    return false;
                }
                l.p0.set(halfWidth, right_y);
            } else {
                l.p0.set(up_x, halfWidth);
            }
        }
        {
            double down_t = (-halfWidth - p.y) / dir.y;
            double down_x = p.x + down_t * dir.x;
            if (down_x < -halfWidth) {
                double left_t = (-halfWidth - p.x) / dir.x;
                double left_y = p.y + left_t * dir.y;
                if (left_y < -halfWidth) {
                    return false;
                }
                l.p1.set(-halfWidth, left_y);
            } else if (down_x > halfWidth) {
                double right_t = (halfWidth - p.x) / dir.x;
                double right_y = p.y + right_t * dir.y;
                if (right_y < -halfWidth) {
                    return false;
                }
                l.p1.set(halfWidth, right_y);
            } else {
                l.p1.set(down_x, -halfWidth);
            }
        }

        return true;
    }

    public static OriLine getVerticalLine(
            Vector2d v,
            OriLine line,
            LineType type) {
        double x0 = line.p0.x;
        double y0 = line.p0.y;
        double x1 = line.p1.x;
        double y1 = line.p1.y;
        double px = v.x;
        double py = v.y;
        Vector2d sub0, sub1, sub, sub0b;

        sub0 = new Vector2d(x0 - px, y0 - py);
        sub1 = new Vector2d(x1 - px, y1 - py);
        sub0b = new Vector2d(-sub0.x, -sub0.y);
        sub = new Vector2d(x1 - x0, y1 - y0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        return new OriLine(
                new OriPoint(x0 + t * sub.x, y0 + t * sub.y),
                new OriPoint(px, py),
                type);
    }

    public static boolean isParallel(Vector2d dir0, Vector2d dir1) {
        // tolerance of 1 degree
        return dir0.angle(dir1) < Math.PI / 180
                || dir0.angle(dir1) > Math.PI * 179.0 / 180;
    }

    public static boolean isLineSegmentsOverlap(Vector2d s0, Vector2d e0,
            Vector2d s1, Vector2d e1) {
        // Whether or not is parallel
        Vector2d dir0 = new Vector2d(e0);
        dir0.sub(s0);
        Vector2d dir1 = new Vector2d(e1);
        dir1.sub(s1);

        if (!isParallel(dir0, dir1)) {
            return false;
        }

        int cnt = 0;
        if (DistanceUtil.DistancePointToSegment(s0, s1, e1) < Constants.EPS) {
            cnt++;
        }
        if (DistanceUtil.DistancePointToSegment(e0, s1, e1) < Constants.EPS) {
            cnt++;
        }
        if (DistanceUtil.DistancePointToSegment(s1, s0, e0) < Constants.EPS) {
            cnt++;
        }
        if (DistanceUtil.DistancePointToSegment(e1, s0, e0) < Constants.EPS) {
            cnt++;
        }

        if (cnt >= 2) {
            return true;
        }
        return false;

    }

    public static boolean isSameLineSegment(OriLine l0, OriLine l1) {
        if (DistanceUtil.Distance(l0.p0, l1.p0) < Constants.EPS
                && DistanceUtil.Distance(l0.p1, l1.p1) < Constants.EPS) {
            return true;
        }
        if (DistanceUtil.Distance(l0.p0, l1.p1) < Constants.EPS
                && DistanceUtil.Distance(l0.p1, l1.p0) < Constants.EPS) {
            return true;
        }

        return false;
    }

    public static boolean isSegmentsCross(Vector2d p0, Vector2d p1, Vector2d q0,
            Vector2d q1) {

        // Rough check
        // Check by coordinates x
        if (p0.x >= p1.x) {
            if ((p0.x < q0.x && p0.x < q1.x) || (p1.x > q0.x && p1.x > q1.x)) {
                return false;
            }
        } else {
            if ((p1.x < q0.x && p1.x < q1.x) || (p0.x > q0.x && p0.x > q1.x)) {
                return false;
            }
        }

        // checked by the coordinate y
        if (p0.y >= p1.y) {
            if ((p0.y < q0.y && p0.y < q1.y) || (p1.y > q0.y && p1.y > q1.y)) {
                return false;
            }
        } else {
            if ((p1.y < q0.y && p1.y < q1.y) || (p0.y > q0.y && p0.y > q1.y)) {
                return false;
            }
        }

        // >= 0.0 means that when p0 == q0, for example, returns false
        if (((p0.x - p1.x) * (q0.y - p0.y) + (p0.y - p1.y) * (p0.x - q0.x))
                * ((p0.x - p1.x) * (q1.y - p0.y)
                        + (p0.y - p1.y) * (p0.x - q1.x)) >= 0.0) {
            return false;
        }

        if (((q0.x - q1.x) * (p0.y - q0.y) + (q0.y - q1.y) * (q0.x - p0.x))
                * ((q0.x - q1.x) * (p1.y - q0.y)
                        + (q0.y - q1.y) * (q0.x - p1.x)) >= 0.0) {
            return false;
        }

        return true;
    }
}
