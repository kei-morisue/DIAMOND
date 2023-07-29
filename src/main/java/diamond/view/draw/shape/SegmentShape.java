/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.Shape;
import java.awt.geom.Line2D;

import diamond.model.XY;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class SegmentShape {

	public static Shape getShape(
			Edge e,
			double scale,
			DrawerBase drawer) {
		XY[] xys = drawer.getXY(e);
		return getShape(xys);
	}

	public static Shape getShape(
			Crease crease,
			double scale,
			DrawerBase drawer) {
		XY[] xys = drawer.getXY(crease);
		return getShape(xys);
	}

	public static Shape getShape(
			Crease crease,
			double scale,
			Double clip,
			DrawerBase drawer) {
		XY[] xys = drawer.getXY(crease);
		return getShape(
				xys,
				clip,
				crease.onEdge0,
				crease.onEdge1);
	}

	private static Shape getShape(
			XY[] xys) {
		XY v1 = xys[0];
		XY v2 = xys[1];
		double x1 = v1.getX();
		double y1 = v1.getY();
		double x2 = v2.getX();
		double y2 = v2.getY();
		Line2D.Double s = new Line2D.Double(x1, y1, x2, y2);
		return s;
	}

	private static Shape getShape(
			XY[] xys,
			double clip,
			boolean clip0,
			boolean clip1) {
		XY w1 = xys[0];
		XY w2 = xys[1];
		XY w = w1.mid(w2);
		XY v1 = clip0 ? w.mid(w1, clip) : w1;
		XY v2 = clip1 ? w.mid(w2, clip) : w2;
		double x1 = v1.getX();
		double y1 = v1.getY();
		double x2 = v2.getX();
		double y2 = v2.getY();
		Line2D.Double s = new Line2D.Double(x1, y1, x2, y2);
		return s;
	}
}
