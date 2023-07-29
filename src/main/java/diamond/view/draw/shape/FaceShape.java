/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import diamond.model.XY;
import diamond.model.fold.Face;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class FaceShape {

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
