/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.Line;
import diamond.model.geom.element.Segment;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class CrossPointUtil {

    // (Including endpoints) intersection between two line segments
    public static Vector2d getCrossPoint(Vector2d p0, Vector2d p1, Vector2d q0,
            Vector2d q1) {
        Vector2d d0 = new Vector2d(p1.x - p0.x, p1.y - p0.y);
        Vector2d d1 = new Vector2d(q1.x - q0.x, q1.y - q0.y);
        Vector2d diff = new Vector2d(q0.x - p0.x, q0.y - p0.y);
        double det = d1.x * d0.y - d1.y * d0.x;

        double epsilon = 1.0e-6;
        if (det * det > epsilon * d0.lengthSquared() * d1.lengthSquared()) {
            // Lines intersect in a single point.  Return both s and t values for
            // use by calling functions.
            double invDet = 1.0 / det;
            double s = (d1.x * diff.y - d1.y * diff.x) * invDet;
            double t = (d0.x * diff.y - d0.y * diff.x) * invDet;

            if (t < 0.0 - epsilon || t > 1.0 + epsilon) {
                return null;
            } else if (s < 0.0 - epsilon || s > 1.0 + epsilon) {
                return null;
            } else {
                Vector2d cp = new Vector2d();
                cp.x = (1.0 - t) * q0.x + t * q1.x;
                cp.y = (1.0 - t) * q0.y + t * q1.y;
                return cp;
            }

        }
        return null;
    }

    public static Vector2d getCrossPoint(OriLine l0, OriLine l1) {
        return getCrossPoint(l0, l1, Constants.EPS);
    }

    private static Vector2d getCrossPoint(OriLine l0, OriLine l1,
            double epsilon) {
        Vector2d p0 = new Vector2d(l0.p0);
        Vector2d p1 = new Vector2d(l0.p1);

        Vector2d d0 = new Vector2d(p1.x - p0.x, p1.y - p0.y);
        Vector2d d1 = new Vector2d(l1.p1.x - l1.p0.x, l1.p1.y - l1.p0.y);
        Vector2d diff = new Vector2d(l1.p0.x - p0.x, l1.p0.y - p0.y);
        double det = d1.x * d0.y - d1.y * d0.x;

        if (det * det > epsilon * d0.lengthSquared() * d1.lengthSquared()) {
            // Lines intersect in a single point.  Return both s and t values for
            // use by calling functions.
            double invDet = 1.0 / det;
            double s = (d1.x * diff.y - d1.y * diff.x) * invDet;
            double t = (d0.x * diff.y - d0.y * diff.x) * invDet;

            if (t < 0.0 - epsilon || t > 1.0 + epsilon) {
                return null;
            } else if (s < 0.0 - epsilon || s > 1.0 + epsilon) {
                return null;
            } else {
                Vector2d cp = new Vector2d();
                cp.x = (1.0 - t) * l1.p0.x + t * l1.p1.x;
                cp.y = (1.0 - t) * l1.p0.y + t * l1.p1.y;
                return cp;
            }

        }
        return null;
    }

    //    Obtain the parameters for the intersection of the segments p0-p1 and q0-q1
    //    The param stores the position of the intersection
    //    Returns false if parallel
    public static boolean getCrossPointParam(Vector2d p0, Vector2d p1,
            Vector2d q0, Vector2d q1, double[] param) {

        Vector2d d0 = new Vector2d(p1.x - p0.x, p1.y - p0.y);
        Vector2d d1 = new Vector2d(q1.x - q0.x, q1.y - q0.y);
        Vector2d diff = new Vector2d(q0.x - p0.x, q0.y - p0.y);
        double det = d1.x * d0.y - d1.y * d0.x;

        double epsilon = Constants.EPS;
        if (det * det > epsilon * d0.lengthSquared() * d1.lengthSquared()) {
            // Lines intersect in a single point.  Return both s and t values for
            // use by calling functions.
            double invDet = 1.0 / det;

            param[0] = (d1.x * diff.y - d1.y * diff.x) * invDet;
            param[1] = (d0.x * diff.y - d0.y * diff.x) * invDet;
            return true;
        }
        return false;

    }

    // Returns the intersection of the semi straight line and the line segment.
    // Null if not intersect
    public static Vector2d getCrossPoint(Line line, Segment seg) {
        Vector2d p0 = new Vector2d(line.p);

        Vector2d d0 = new Vector2d(line.dir);
        Vector2d d1 = new Vector2d(seg.ev.x - seg.sv.x, seg.ev.y - seg.sv.y);
        Vector2d diff = new Vector2d(seg.sv.x - p0.x, seg.sv.y - p0.y);
        double det = d1.x * d0.y - d1.y * d0.x;

        double epsilon = Constants.EPS;
        if (det * det > epsilon * d0.lengthSquared() * d1.lengthSquared()) {
            // Lines intersect in a single point.  Return both s and t values for
            // use by calling functions.
            double invDet = 1.0 / det;
            double s = (d1.x * diff.y - d1.y * diff.x) * invDet;
            double t = (d0.x * diff.y - d0.y * diff.x) * invDet;

            if (t < 0.0 - epsilon || t > 1.0 + epsilon) {
                return null;
            } else if (s < 0.0 - epsilon) {
                return null;
            } else {
                Vector2d cp = new Vector2d();
                cp.x = (1.0 - t) * seg.sv.x + t * seg.ev.x;
                cp.y = (1.0 - t) * seg.sv.y + t * seg.ev.y;
                return cp;
            }
        }
        return null;
    }

    // Compute the intersection of straight lines
    public static Vector2d getCrossPoint(Line l0, Line l1) {
        Vector2d p0 = new Vector2d(l0.p);
        Vector2d p1 = new Vector2d(l0.p);
        p1.add(l0.dir);

        Vector2d d0 = new Vector2d(p1.x - p0.x, p1.y - p0.y);
        Vector2d d1 = new Vector2d(l1.dir);
        Vector2d diff = new Vector2d(l1.p.x - p0.x, l1.p.y - p0.y);
        double det = d1.x * d0.y - d1.y * d0.x;

        double epsilon = Constants.EPS;
        if (det * det > epsilon * d0.lengthSquared() * d1.lengthSquared()) {
            // Lines intersect in a single point.  Return both s and t values for
            // use by calling functions.
            double invDet = 1.0 / det;
            double t = (d0.x * diff.y - d0.y * diff.x) * invDet;

            Vector2d cp = new Vector2d();
            cp.x = (1.0 - t) * l1.p.x + t * (l1.p.x + l1.dir.x);
            cp.y = (1.0 - t) * l1.p.y + t * (l1.p.y + l1.dir.y);
            return cp;
        }
        return null;
    }

    public static int getCrossPoint(Vector2d ap1, Vector2d ap2,
            Vector2d p1, Vector2d p2, Vector2d p3, Vector2d p4) {

        if (DistanceUtil.Distance(p1, p3) < Constants.EPS
                && DistanceUtil.Distance(p2, p4) < Constants.EPS) {
            ap1.set(p1);
            ap2.set(p2);
            return 2;
        }

        if (DistanceUtil.Distance(p1, p4) < Constants.EPS
                && DistanceUtil.Distance(p2, p3) < Constants.EPS) {
            ap1.set(p1);
            ap2.set(p2);
            return 2;
        }

        double a1, a2, b1, b2;

        a1 = (p1.y - p2.y);
        a2 = (p3.y - p4.y);
        b1 = (p2.x - p1.x);
        b2 = (p4.x - p3.x);

        if (Math.abs(a1 * b2 - a2 * b1) < Constants.EPS) {
            if (Math.max(p1.x, p2.x) < Math.min(p3.x, p4.x)
                    || Math.max(p1.y, p2.y) < Math.min(p3.y, p4.y)
                    || Math.max(p3.x, p4.x) < Math.min(p1.x, p2.x)
                    || Math.max(p3.y, p4.y) < Math.min(p1.y, p2.y)) {
                return 0;
            }

            if (isRange(p3, p4, p1)) {
                ap1.set(p1);
            } else if (isRange(p1, p4, p3)) {
                ap1.set(p3);
            } else if (isRange(p1, p3, p4)) {
                ap1.set(p4);
            } else {
                return 0;
            }

            if (isRange(p3, p4, p2)) {
                ap2.set(p2);
            } else if (isRange(p2, p4, p3)) {
                ap2.set(p3);
            } else if (isRange(p2, p3, p4)) {
                ap2.set(p4);
            } else {
                return 0;
            }
            return 2;
        }

        double c1, c2;

        c1 = p1.x * p2.y - p2.x * p1.y;
        c2 = p3.x * p4.y - p4.x * p3.y;

        ap1.x = (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
        ap1.y = (a1 * c2 - a2 * c1) / (a2 * b1 - a1 * b2);

        if (isRange(p1, p2, ap1) && isRange(p3, p4, ap1)) {
            return 1;
        } else {
            return 0;
        }
    }

    //  Determine if p3 is in p1~p2 range
    public static boolean isRange(double p1, double p2, double p3) {
        return Math.min(p1, p2) <= p3 && p3 <= Math.max(p1, p2);
    }

    public static boolean isRange(Vector2d p1, Vector2d p2, Vector2d p3) {
        return isRange(p1.x, p2.x, p3.x) && isRange(p1.y, p2.y, p3.y);
    }

}
