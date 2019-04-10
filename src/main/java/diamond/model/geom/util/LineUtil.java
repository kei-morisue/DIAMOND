/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class LineUtil {
    private static void set(Point2D.Double p0, double x, double y) {//TODO
        p0.x = x;
        p0.y = y;
    }

    // Returns false if nothing is in the clip area
    public static boolean clipLine(OriLine l, double halfWidth) {
        OriPoint p0 = l.p0;
        OriPoint p1 = l.p1;
        OriPoint dir = new OriPoint();
        dir.x = p1.x - p0.x;
        dir.y = p1.y - p0.y;

        // If horizontal
        if (Math.abs(dir.y) < Constants.EPS) {
            if (p0.y < -halfWidth || p0.y > halfWidth) {
                return false;
            }

            set(p0, -halfWidth, p0.y);
            set(p1, halfWidth, p0.y);
            return true;
        }
        // If vertical
        if (Math.abs(dir.x) < Constants.EPS) {
            if (p0.x < -halfWidth || p0.x > halfWidth) {
                return false;
            }

            set(p0, p0.x, -halfWidth);
            set(p1, p0.x, halfWidth);
            return true;
        }

        // If you do not have any horizontal vertical
        // Cut down
        {
            double up_t = (halfWidth - p0.y) / dir.y;
            double up_x = p0.x + up_t * dir.x;

            if (up_x < -halfWidth) {
                double left_t = (-halfWidth - p0.x) / dir.x;
                double left_y = p0.y + left_t * dir.y;
                if (left_y < -halfWidth) {
                    return false;
                }
                set(p0, -halfWidth, left_y);
            } else if (up_x > halfWidth) {
                double right_t = (halfWidth - p0.x) / dir.x;
                double right_y = p0.y + right_t * dir.y;
                if (right_y < -halfWidth) {
                    return false;
                }
                set(p0, halfWidth, right_y);
            } else {
                set(p0, up_x, halfWidth);
            }
        }
        {
            double down_t = (-halfWidth - p0.y) / dir.y;
            double down_x = p0.x + down_t * dir.x;
            if (down_x < -halfWidth) {
                double left_t = (-halfWidth - p0.x) / dir.x;
                double left_y = p0.y + left_t * dir.y;
                if (left_y < -halfWidth) {
                    return false;
                }
                set(p1, -halfWidth, left_y);
            } else if (down_x > halfWidth) {
                double right_t = (halfWidth - p0.x) / dir.x;
                double right_y = p0.y + right_t * dir.y;
                if (right_y < -halfWidth) {
                    return false;
                }
                set(p1, halfWidth, right_y);
            } else {
                set(p1, down_x, -halfWidth);
            }
        }

        return true;
    }

    public static OriLine getVerticalLine(
            OriPoint v,
            OriLine line,
            LineType type) {
        double x0 = line.p0.x;
        double y0 = line.p0.y;
        double x1 = line.p1.x;
        double y1 = line.p1.y;
        double px = v.x;
        double py = v.y;
        Vector2d sub0, sub, sub0b;

        sub0 = new Vector2d(x0 - px, y0 - py);

        sub0b = new Vector2d(-sub0.x, -sub0.y);
        sub = new Vector2d(x1 - x0, y1 - y0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        return new OriLine(
                new OriPoint(x0 + t * sub.x, y0 + t * sub.y),
                new OriPoint(px, py),
                type);
    }

    public static boolean isParallel(OriPoint dir0, OriPoint dir1) {
        // tolerance of 1 degree
        return Math.abs(Math.sin(dir0.angle(dir1))) < 0.001;
    }

    public static boolean isLineSegmentsOverlap(
            OriPoint s0,
            OriPoint e0,
            OriPoint s1,
            OriPoint e1) {
        // Whether or not is parallel
        OriPoint dir0 = e0.sub(s0);
        OriPoint dir1 = e1.sub(s1);

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
