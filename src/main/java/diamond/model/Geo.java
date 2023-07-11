/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import java.util.List;

import diamond.model.line.Line;

/**
 * @author Kei Morisue
 *
 */
public class Geo {
	public static final double EPS = 1.0e-16;

	public static double minLength(List<Line> lines) {
		Double minSq = null;
		for (Line line : lines) {
			XY v0 = line.p;
			XY v1 = line.q;
			double lenSq = v0.distSq(v1);
			if (minSq == null || minSq > lenSq) {
				minSq = lenSq;
				continue;
			}
		}
		return Math.sqrt(minSq);

	}

	public static boolean isZero(double a) {
		return Math.abs(a) < EPS;
	}

	public static boolean isClose(double a, double b, double eps) {
		return Math.abs(a - b) < eps;
	}

	public static boolean isClose(XY a, XY b, double eps) {
		double dx = a.getX() - b.getX();
		double dy = a.getY() - b.getY();
		return Math.abs(dx) < eps && Math.abs(dy) < eps;
	}

	public static boolean on(XY a, XY b, XY c, double eps) {
		Dir v = a.dir(b);
		if (a.dir(c).dot(v) > 0 == b.dir(c).dot(v) > 0) {
			return false;// c is outside the segment (a, b)
		}
		double dot = v.perp().unit().dot(a.dir(c));
		return Math.abs(dot) <= eps;
	}

	public static XY intersect(XY a, XY b, XY c, XY d, double eps) {
		if (isClose(a, c, eps) || isClose(a, d, eps) || isClose(b, c, eps) || isClose(b, d, eps)) {
			return null;
		}
		if (on(a, b, c, eps) || on(a, b, d, eps) || on(c, d, a, eps) || on(c, d, b, eps)) {
			return null;
		}

		Dir cd = c.dir(d);
		Dir ab = a.dir(b);
		double denom = cd.cross(ab);
		if (isZero(denom)) {
			return null;// aligned or parallel
		}

		Dir ac = a.dir(c);
		double sNum = cd.cross(ac);
		if (isZero(sNum) || isZero(sNum - denom)) {
			return null;// the intersection is an extreme point
		}
		double tNum = -ac.cross(ab);
		if (isZero(tNum) || isZero(tNum - denom)) {
			return null;
		}
		double s = sNum / denom;
		double t = tNum / denom;
		if (s < 0 || 1 < s || t < 0 || 1 < t) {
			return null;// intersects outside the segment
		}
		XY p = a.mid(b, s);
		if (isClose(a, p, eps) || isClose(b, p, eps) || isClose(c, p, eps) || isClose(d, p, eps)) {
			return null;
		}
		return p;
	}
}
