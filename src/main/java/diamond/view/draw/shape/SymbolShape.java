/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

import diamond.model.Dir;
import diamond.model.XY;
import diamond.model.fold.symbol.ArrowMV;
import diamond.model.fold.symbol.Circle;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class SymbolShape {
	public static double RADIUS_CIRCLE = 30.0;
	public static int SIZE_ARROW_HEAD = 60;
	public static int CURTOSIS_ARROW_HEAD = 14;

	public static Ellipse2D.Double getShape(
			Circle circle,
			double scale,
			DrawerBase drawer) {
		XY c = drawer.getXY(circle.getVertex());
		double r = RADIUS_CIRCLE / scale;
		Ellipse2D.Double s
				= new Ellipse2D.Double(c.x - r / 2, c.y - r / 2, r, r);
		return s;
	}

	public static Shape getBodyShape(
			ArrowMV arrowMV,
			double scale,
			DrawerBase drawer) {
		XY[] th = arrowMV.getTailHead(drawer, scale);
		XY tail = th[0];
		XY head = th[1];
		XY c = arrowMV.getControlPoint(tail, head);
		GeneralPath path = new GeneralPath();
		path.moveTo(tail.x, tail.y);
		path.curveTo(tail.x, tail.y, c.x, c.y, head.x, head.y);
		return path;
	}

	public static Shape getHeadShape(
			ArrowMV arrowMV,
			double scale,
			DrawerBase drawer) {
		XY[] th = arrowMV.getTailHead(drawer, scale);
		XY tail = th[0];
		XY head = th[1];
		XY c = arrowMV.getControlPoint(tail, head);
		double theta = head.dir(c).angle();
		Dir d0 = new Dir(Math.PI / CURTOSIS_ARROW_HEAD + theta)
				.mul(SIZE_ARROW_HEAD / scale);
		Dir d1 = new Dir(-Math.PI / CURTOSIS_ARROW_HEAD + theta)
				.mul(SIZE_ARROW_HEAD / scale);
		XY a = d0.ver(head);
		XY b = d1.ver(head);
		GeneralPath path = new GeneralPath();
		path.moveTo(head.x, head.y);
		path.lineTo(b.x, b.y);
		path.lineTo(a.x, a.y);
		path.closePath();
		return path;
	}

}
