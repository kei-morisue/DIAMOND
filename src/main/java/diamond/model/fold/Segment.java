/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;

import diamond.model.Dir;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public abstract class Segment extends Renderable implements Serializable {
	public static final int NONE = 0;
	public static final int MOUNTAIN = 1;
	public static final int VALLEY = -1;
	protected Vertex v0;
	protected Vertex v1;
	protected int a;

	public Segment(Vertex v0, Vertex v1, int a) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.a = a;
	}

	public boolean isValley() {
		return a == VALLEY;
	}

	@Override
	public XY centroid() {
		return v0.p.mid(v1.p);
	}

	public Dir dir() {
		return v0.p.dir(v1.p);
	}

	public Dir dirF() {
		return v0.f.dir(v1.f);
	}

	public Vertex getV0() {
		return v0;
	}

	public Vertex getV1() {
		return v1;
	}

	public Vertex getPair(
			Vertex v) {
		if (v == v0) {
			return v1;
		}
		if (v == v1) {
			return v0;
		}
		return null;
	}

}
