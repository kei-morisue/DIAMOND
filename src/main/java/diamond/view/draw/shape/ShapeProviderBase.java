/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import diamond.model.XY;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public abstract class ShapeProviderBase {
	abstract protected XY getXY(Vertex v);

	public Line2D.Double getShape(Edge e, double scale) {
		XY v1 = getXY(e.getV0());
		XY v2 = getXY(e.getV1());
		double x1 = v1.getX();
		double y1 = v1.getY();
		double x2 = v2.getX();
		double y2 = v2.getY();
		Line2D.Double s = new Line2D.Double(x1, y1, x2, y2);
		return s;
	}

	public Ellipse2D.Double getShape(Vertex vertex, double scale) {
		XY xy = getXY(vertex);
		double r = 10 / scale;
		double x = xy.getX();
		double y = xy.getY();
		Ellipse2D.Double s = new Ellipse2D.Double(x - r / 2, y - r / 2, r, r);
		return s;
	}

	public GeneralPath getShape(Face face, double scale) {
		GeneralPath path = null;
		ArrayList<Vertex> vertices = face.getVertices();
		for (Vertex vertex : vertices) {
			XY xy = getXY(vertex);
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
