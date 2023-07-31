/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import diamond.model.Dir;
import diamond.model.Geo;
import diamond.model.XY;
import diamond.model.fold.symbol.SymbolBase;
import diamond.view.draw.DrawerBase;

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

	public void setEdges() {
		for (int i = 0; i < vertices.size(); i++) {
			Vertex v1 = vertices.get(i);
			Vertex v2 = vertices.get((i + 1) % vertices.size());
			Edge edge = v1.getEdge(v2);
			add(edge);
		}
	}

	private void add(
			Edge edge) {
		edges.add(edge);
		if (edge.getF0() == null) {
			edge.setF0(this);// Edge to Face #0
		} else {
			edge.setF1(this);// Edge to Face #1
		}
	}

	public void add(
			Crease crease) {
		creases.add(crease);
		crease.setFace(this);
	}

	public Edge getBaseEdge() {
		return edges.get(0);
	}

	public void forVertices(
			Consumer<? super Vertex> action) {
		vertices.forEach(action);
	}

	public void forCreases(
			Consumer<? super Crease> action) {
		creases.forEach(action);
	}

	public void forEdges(
			Consumer<? super Edge> action) {
		edges.forEach(action);
	}

	public boolean swapWrongPair(
			List<Face> faces) {
		int i = faces.indexOf(this);
		boolean flip = isFlip;
		for (Edge edge : edges) {
			Face fj = edge.getPair(this);
			if (fj == this) {
				continue;
			}
			boolean isValley = edge.isValley();
			int j = faces.indexOf(fj);
			if (!(flip ^ isValley) && i < j
					|| flip ^ isValley && j < i) {
				Collections.swap(faces, i, j);
				return true;
			}
		}
		return false;
	}

	public boolean isInside(
			XY p) {
		Double angleSum = 0.0;
		XY p0 = vertices.get(vertices.size() - 1);
		Dir dir0 = p.dir(p0).unit();
		for (Vertex v : vertices) {
			Dir dir = p.dir(v).unit();
			double dot = dir0.dot(dir);
			dot = dot > 1 ? 1 : dot;
			dot = dot < -1 ? -1 : dot;
			double cross = dir0.cross(dir);
			double delta = Math.acos(dot);
			delta = cross < 0 ? -delta : delta;
			angleSum += delta;
			dir0 = dir;
		}
		return Math
				.abs(Math.abs(angleSum / (2 * Math.PI)) - 1) < Geo.RADIAN_EPS;
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

	public void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale,
			Collection<SymbolBase> symbols) {
		drawer.draw(g2d, this, scale);
		edges.forEach(edge -> {
			edge.accept(drawer, g2d, scale);
		});
		creases.forEach(crease -> {
			crease.accept(drawer, g2d, scale);
		});
		symbols.forEach(symbol -> {
			symbol.accept(drawer, g2d, scale);
		});
	};

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
