/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D;

import diamond.model.geom.Constants;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;

/**
 * @author long_
 *
 */
public class OriLineUtil {
    private static void set(Point2D.Double p0, double x, double y) {
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
        OriPoint p0 = line.p0;
        OriPoint p1 = line.p1;
        OriPoint sub0, sub, sub0b;

        sub0 = p0.sub(v);
        sub0b = sub0.negate();
        sub = p1.sub(p0);

        double t = ((sub.x * sub0b.x) + (sub.y * sub0b.y))
                / ((sub.x * sub.x) + (sub.y * sub.y));

        return new OriLine(p0.add(sub.scale(t)), v, type);
    }

    public static boolean isParallel(OriPoint dir0, OriPoint dir1) {
        return Math.abs(Math.sin(dir0.angle(dir1))) < Constants.EPS;
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

    public static boolean isConnected(OriLine l0, OriLine l1) {
        if (DistanceUtil.Distance(l0.p0, l1.p0) < Constants.EPS) {
            return true;
        }
        if (DistanceUtil.Distance(l0.p0, l1.p1) < Constants.EPS) {
            return true;
        }
        if (DistanceUtil.Distance(l0.p1, l1.p0) < Constants.EPS) {
            return true;
        }
        if (DistanceUtil.Distance(l0.p1, l1.p1) < Constants.EPS) {
            return true;
        }
        return false;
    }

    public static OriLine mirroredLine(OriLine line,
            OriLine baseOriLine) {
        OriPoint q0 = OriPointUtil.mirroredPoint(line.p0, baseOriLine);
        OriPoint q1 = OriPointUtil.mirroredPoint(line.p1, baseOriLine);
        return new OriLine(q0, q1, line.getType());
    }
}
