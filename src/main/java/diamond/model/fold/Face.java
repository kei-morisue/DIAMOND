/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;

import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Face extends Renderable implements Comparable<Face>, Serializable {
	private ArrayList<Vertex> vertices;

	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Crease> creases = new ArrayList<Crease>();

	public boolean isFlip;
	transient public boolean isFolded;

	public Face(ArrayList<Vertex> vertices) {
		super();
		this.vertices = vertices;
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public ArrayList<Crease> getCreases() {
		return creases;
	}

	public boolean isInside(
			XY p) {
		Double a0 = 0.0;
		XY p0 = vertices.get(vertices.size() - 1).p;
		for (Vertex v : vertices) {
			double a = p0.dir(p).cross(p0.dir(v.p));
			if (a0 == 0.0) {
				a0 = a;
			}
			if (a0 * a <= 0) {
				return false;
			}
			p0 = v.p;
		}
		return true;
	}

	@Override
	public XY centroid() {
		double x = 0.0;
		double y = 0.0;
		for (Vertex v : vertices) {
			x += v.p.x;
			y += v.p.y;
		}
		int n = vertices.size();
		return new XY(x / n, y / n);
	}

	public double area2() {
		double area = 0.0;
		XY xy0 = vertices.get(vertices.size() - 1).p;
		for (Vertex p1 : vertices) {
			XY xy1 = p1.p;
			area += (xy0.getX() + xy1.getX()) * (xy1.getY() - xy0.getY());
			xy0 = xy1;
		}
		return area;

	}

	@Override
	public int compareTo(
			Face f) {
		return f.area2() - area2() < 0 ? -1 : 1;
	}

}
