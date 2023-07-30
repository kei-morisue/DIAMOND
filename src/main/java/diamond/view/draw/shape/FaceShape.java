/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import diamond.model.XY;
import diamond.model.fold.Face;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class FaceShape {

	public static Shape getBaseFaceSymbol(
			Face face,
			double scale) {
		XY c = face.centroid();
		return getBaseFaceSymbol(c, scale);
	}

	public static Shape getBaseFaceSymbol(
			XY c,
			double scale) {
		double r = 2 / scale;
		Rectangle2D s1
				= new Rectangle2D.Double(c.x - 3 * r, c.y - r, 6 * r, 2 * r);
		Rectangle2D s2
				= new Rectangle2D.Double(c.x - r, c.y - 3 * r, 2 * r, 6 * r);

		Area area = new Area(s1);
		area.add(new Area(s2));
		return area;
	}

	public static Shape getShape(
			Face face,
			double scale,
			DrawerBase drawer) {
		GeneralPath path = null;
		ArrayList<XY> vertices = drawer.getXY(face);
		for (XY xy : vertices) {
			if (path == null) {
				path = new GeneralPath();
				path.moveTo(xy.getX(), xy.getY());
			} else {
				path.lineTo(xy.getX(), xy.getY());
			}

		}
		path.closePath();
		return path;
	}

	public static Shape getShape(
			Face face,
			double scale,
			double clip,
			DrawerBase drawer) {
		GeneralPath path = null;
		XY c = face.centroid();
		ArrayList<XY> vertices = drawer.getXY(face);
		for (XY v : vertices) {
			XY xy = c.mid(v, clip);
			if (path == null) {
				path = new GeneralPath();
				path.moveTo(xy.getX(), xy.getY());
			} else {
				path.lineTo(xy.getX(), xy.getY());
			}

		}
		path.closePath();
		return path;
	}
}
