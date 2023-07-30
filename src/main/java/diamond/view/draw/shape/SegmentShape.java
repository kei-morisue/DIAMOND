/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.geom.Line2D;

import diamond.model.XY;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Segment;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class SegmentShape {
	static final float[] DASH_V = { 10f, 3f };
	static final float[] DASH_M = { 10f, 2f, 2f, 2f };

	private static float[] scaledDashM(
			float scale) {
		return new float[] { DASH_M[0] / scale, DASH_M[1] / scale,
				DASH_M[2] / scale, DASH_M[3] / scale };
	}

	private static float[] scaledDashV(
			float scale) {
		return new float[] { DASH_V[0] / scale, DASH_V[1] / scale };
	}

	public static BasicStroke getStroke(
			Crease crease,
			double scale) {
		if (crease.getA() == Segment.NONE) {
			BasicStroke stroke = new BasicStroke((float) (0.0 / scale),
					BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
			return stroke;

		}
		BasicStroke stroke = new BasicStroke((float) (2.0 / scale),
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,
				2,
				crease.isValley() ? scaledDashV((float) scale)
						: scaledDashM((float) scale),
				0.0f);
		return stroke;

	}

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
				crease.getA() == Segment.NONE ? clip : 1.0,
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
