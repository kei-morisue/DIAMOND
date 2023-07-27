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

	public void setF0(
			Face f0) {
		this.f0 = f0;
	}

	public void setF1(
			Face f1) {
		this.f1 = f1;
	}

	public int getA() {
		return a;
	}

	public Face getF0() {
		return f0;
	}

	public Face getF1() {
		return f1;
	}

}
