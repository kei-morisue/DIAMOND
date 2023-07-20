/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.HashSet;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class VertexOffsetter {
	private static final double r0 = 15.0;
	private static final double r1 = 5.0;

	public static void offset(Cp cp, Vertex v) {
		HashSet<Vertex> selected = select(v, cp);
		double scale = cp.getTransform().getScale();
		for (Vertex vertex : selected) {
			double depth = .0;
			Point2D.Double dir = new Point2D.Double();
			for (HalfEdge he : vertex.getHalfEdges()) {
				depth += cp.getFaces().indexOf(he.getFace());
				Double v0 = he.getV0().getFolded();
				Double v1 = he.getV1().getFolded();
				dir = Point2DUtil.add(dir, Point2DUtil.sub(v1, v0));
			}
			int size = vertex.getHalfEdges().size();
			depth = depth / size;
			dir = Point2DUtil.normalize(Point2DUtil.scale(dir, 1.0 / size));
			dir = Point2DUtil.scale(dir, (depth * (r0 + r1) / size - r1) / scale);
			vertex.setOffset(dir);
		}
	}

	public static void reset(Cp cp, Vertex v) {
		HashSet<Vertex> selected = select(v, cp);
		for (Vertex vertex : selected) {
			vertex.setOffset(new Point2D.Double());
		}
	}

	private static HashSet<Vertex> select(Vertex v, Cp cp) {
		HashSet<Vertex> vertices = cp.getVertices();
		HashSet<Vertex> selected = new HashSet<Vertex>();
		for (Vertex v0 : vertices) {
			if (Fuzzy.around(v.getFolded().distance(v0.getFolded()), .0)) {
				selected.add(v0);
			}
		}
		return selected;
	}

}
