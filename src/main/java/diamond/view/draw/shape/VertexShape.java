/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import diamond.model.XY;
import diamond.model.fold.Vertex;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class VertexShape {
	public static Shape getShape(
			Vertex vertex,
			double radius,
			double scale,
			DrawerBase drawer) {
		XY xy = drawer.getXY(vertex);
		double size = radius / scale;
		double x = xy.getX();
		double y = xy.getY();
		Ellipse2D.Double s
				= new Ellipse2D.Double(x - size / 2, y - size / 2, size, size);
		return s;
	}
}
