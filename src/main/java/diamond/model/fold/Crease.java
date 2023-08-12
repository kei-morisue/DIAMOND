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
public class Crease extends Segment implements Serializable {

	private Face face;

	public Crease(Vertex v0, Vertex v1, int a) {
		super(v0, v1, a);
	}

	public Crease(Crease crease) {
		super(crease.v0, crease.v1, crease.a);
	}

	public void setFace(
			Face face) {
		this.face = face;
	}

	public Face getFace() {
		return face;
	}

	public void restore() {
		v0.addAdj(v1);
		v0.put(v1, this);
		v1.put(v0, this);
	}

	@Override
	public void flip() {
		a = ((a + 2) % 3) - 1;
	}

	@Override
	public void add(
			Vertex v0,
			Vertex v1,
			Collection<Edge> edges,
			Collection<Crease> creases) {
		creases.add(new Crease(v0, v1, a));
	}

	@Override
	public void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale) {
		drawer.draw(g2d, this, scale);
	};

	@Override
	public boolean isEdge() {
		return false;
	}

	@Override
	public Segment getFlip() {
		return new Edge(v0, v1, a);
	}

	@Override
	public Face getTopFace(
			List<Face> faces) {
		return face;
	}
}
