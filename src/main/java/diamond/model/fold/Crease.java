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

}