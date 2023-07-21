/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * @author Kei Morisue
 *
 */
public class XY extends Point2D.Double implements Serializable {

	public XY(double x, double y) {
		super(x, y);
	}

	public Dir dir(XY v) {
		return new Dir(v.x - x, v.y - y);
	}

	public double distSq(XY v0) {
		return dir(v0).mgSq();

	}

	public XY mid(XY v, double t) {
		return dir(v).mul(t).ver(this);
	}

	public XY mid(XY v) {
		return mid(v, 0.5);
	}

	public class Comparator implements java.util.Comparator<XY> {
		private double eps;

		public Comparator(double eps) {
			this.eps = eps;
		}

		@Override
		public int compare(XY p1, XY p2) {
			return Geo.isClose(p1.x, p2.x, eps) ? (int) (p1.y - p2.y) : (int) (p1.x - p2.x);
		}
	}
}
