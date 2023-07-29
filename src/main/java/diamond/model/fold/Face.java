/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;

import diamond.model.Dir;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Comparable<Face>, Serializable, Renderable {
	private ArrayList<Vertex> vertices;

	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Crease> creases = new ArrayList<Crease>();

	public boolean isFlip;
	transient public boolean isFolded;
	transient private boolean isPicked;

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
		Double angleSum = 0.0;
		XY p0 = vertices.get(vertices.size() - 1);
		Dir dir0 = p.dir(p0).unit();
		for (Vertex v : vertices) {
			Dir dir = p.dir(v).unit();
			double dot = dir0.dot(dir);
			double cross = dir0.cross(dir);
			double delta = Math.acos(dot);
			delta = cross < 0 ? -delta : delta;
			angleSum += delta;
			dir0 = dir;
		}
		return Math.abs(Math.abs(angleSum / (2 * Math.PI)) - 1) < 1e-10;
	}

	@Override
	public XY centroid() {
		double x = 0.0;
		double y = 0.0;
		for (Vertex v : vertices) {
			x += v.x;
			y += v.y;
		}
		int n = vertices.size();
		return new XY(x / n, y / n);
	}

	public double area2() {
		double area = 0.0;
		XY xy0 = vertices.get(vertices.size() - 1);
		for (Vertex p1 : vertices) {
			XY xy1 = p1;
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

	@Override
	public boolean isPicked() {
		return isPicked;
	}

	@Override
	public void setPicked(
			boolean picked) {
		isPicked = picked;

	}

}
