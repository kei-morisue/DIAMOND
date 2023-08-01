/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class Edge extends Segment implements Serializable {
	private Face f0;
	private Face f1;

	public Edge(Vertex v0, Vertex v1, int a) {
		super(v0, v1, a);
	}

	public Edge(Edge edge) {
		super(edge.v0, edge.v1, edge.a);
	}

	public Face getPair(
			Face face) {
		if (face == f0) {
			return f1;
		}
		if (face == f1) {
			return f0;
		}
		return null;

	}

	@Override
	public void flip() {
		a = -a;
	}

	public void setF0(
			Face f0) {
		this.f0 = f0;
	}

	public void setF1(
			Face f1) {
		this.f1 = f1;
	}

	public Face getF0() {
		return f0;
	}

	public Face getF1() {
		return f1;
	}

	@Override
	public void add(
			Vertex v0,
			Vertex v1,
			Collection<Edge> edges,
			Collection<Crease> creases) {
		edges.add(new Edge(v0, v1, a));
	}

	public void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale) {
		drawer.draw(g2d, this, scale);
		v0.accept(drawer, g2d, scale);
		v1.accept(drawer, g2d, scale);
	};

	public boolean isBoundary() {
		return a == NONE;
	}

	@Override
	public boolean isEdge() {
		return true;
	}

	@Override
	public Segment getFlip() {
		return new Crease(v0, v1, a);
	}

	@Override
	public Face getTopFace(
			List<Face> faces) {
		int i0 = faces.indexOf(f0);
		int i1 = faces.indexOf(f1);
		return i1 > i0 ? f1 : f0;
	}

}
