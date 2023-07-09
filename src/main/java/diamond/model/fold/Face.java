/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;

import diamond.model.XY;
import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public class Face implements Renderable, Comparable<Face>, Serializable {
	private ArrayList<Vertex> vertices;

	private ArrayList<Edge> edges = new ArrayList<Edge>();

	private boolean flip;

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

	public boolean isFlip() {
		return flip;
	}

	public void setFlip(boolean flip) {
		this.flip = flip;
	}

	public double area2() {
		double area = 0.0;
		XY xy0 = vertices.get(vertices.size() - 1).getV();
		for (Vertex p1 : vertices) {
			XY xy1 = p1.getV();
			area += (xy0.getX() + xy1.getX()) * (xy1.getY() - xy0.getY());
			xy0 = xy1;
		}
		return area;

	}

	@Override
	public int compareTo(Face f) {
		return f.area2() - area2() < 0 ? -1 : 1;
	}

	@Override
	public Shape getShape(ShapeProviderBase shapeProvider, double scale) {
		return shapeProvider.getShape(this, scale);
	}

	@Override
	public Color getColor(ColorProviderBase colorProvider) {
		return colorProvider.getColor(this);
	}
}
