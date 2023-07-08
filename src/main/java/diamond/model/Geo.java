/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import java.util.List;

/**
 * @author Kei Morisue
 *
 */
public class Geo {
	public static double minLength(List<XY> p, List<XY> q) {
		Double minSq = null;
		for (int i = 0; i < p.size(); i++) {
			XY v0 = p.get(i);
			XY v1 = q.get(i);
			double lenSq = v0.distSq(v1);
			if (minSq == null || minSq > lenSq) {
				minSq = lenSq;
				continue;
			}
		}
		return Math.sqrt(minSq);

	}

	public static boolean isZero(double a) {
		return Math.abs(a) < 1.0e-16;
	}

	public static boolean close(XY a, XY b, double eps) {
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
		if (close(a, c, eps) || close(a, d, eps) || close(b, c, eps) || close(b, d, eps)) {
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
		if (close(a, p, eps) || close(b, p, eps) || close(c, p, eps) || close(d, p, eps)) {
			return null;
		}
		return p;
	}
}
