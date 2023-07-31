/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.geom.Ellipse2D;

import diamond.model.XY;
import diamond.model.fold.symbol.Circle;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class SymbolShape {
	public static double RADIUS_CIRCLE = 30.0;

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

}
