/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import diamond.model.Geo;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public abstract class Segment extends Line implements Serializable, Renderable {
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

	public boolean add(
			Cp cp) {
		Set<Segment> segs = cp.getSegments();
		if (Geo.isClose(v0, v1, cp.eps)) {
			return false;
		}
		segs.add(this);
		cp.rebuild(segs);
		return true;
	};

	public boolean remove(
			Cp cp) {
		Set<Segment> segs = cp.getSegments();
		if (segs.remove(this)) {
			cp.rebuild(segs);
			return true;
		}
		return false;

	};

	public abstract boolean isEdge();

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
