/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;

/**
 * @author Kei Morisue
 *
 */
public abstract class Segment extends Renderable implements Serializable {

	protected Vertex v0;
	protected Vertex v1;

	public Segment(Vertex v0, Vertex v1) {
		super();
		this.v0 = v0;
		this.v1 = v1;
	}

	public Vertex getV0() {
		return v0;
	}

	public Vertex getV1() {
		return v1;
	}

	public Vertex getPair(Vertex v) {
		if (v == v0) {
			return v1;
		}
		if (v == v1) {
			return v0;
		}
		return null;
	}

}
