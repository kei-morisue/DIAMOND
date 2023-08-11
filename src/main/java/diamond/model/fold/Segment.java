/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.List;

import diamond.model.XY;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public abstract class Segment extends Line implements Renderable {
	public static final int NONE = 0;
	public static final int MOUNTAIN = 1;
	public static final int VALLEY = -1;

	protected int a;

	transient protected boolean isPicked = false;

	public Segment(Vertex v0, Vertex v1, int a) {
		super(v0, v1);
		this.a = a;
	}

	public boolean isValley() {
		return a == VALLEY;
	}

	public abstract void flip();

	public abstract Segment getFlip();

	public abstract boolean isEdge();

	public abstract void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale);

	public abstract Face getTopFace(
			List<Face> faces);

	public abstract void add(
			Vertex v0,
			Vertex v1,
			Collection<Edge> edges,
			Collection<Crease> creases);

	@Override
	public XY centroid() {
		return v0.mid(v1);
	}

	@Override
	public boolean isPicked() {
		return isPicked;
	}

	@Override
	public void setPicked(
			boolean picked) {
		this.isPicked = picked;
	}

	public int getA() {
		return a;
	}

}
