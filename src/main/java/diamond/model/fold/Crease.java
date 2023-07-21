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
public class Crease extends Segment implements Serializable {
	public static enum Assign implements Serializable {
		NONE, MOUNTAIN, VALLEY
	};

	private Assign a;
	private Face face;

	public Crease(Vertex v0, Vertex v1, Assign a) {
		super(v0, v1);
		this.a = a;
	}

	public Crease(Crease crease) {
		super(crease.v0, crease.v1);
		this.a = crease.a;
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public Assign getA() {
		return a;
	}

	public Face getFace() {
		return face;
	}

}
