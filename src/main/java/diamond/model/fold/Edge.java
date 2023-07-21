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
	public static enum Assign implements Serializable {
		MOUNTAIN, VALLEY, BOUND
	};

	private Assign a;
	private Face f0;
	private Face f1;

	public Edge(Vertex v0, Vertex v1, Assign a) {
		super(v0, v1);
		this.a = a;
	}

	public Edge(Edge edge) {
		super(edge.v0, edge.v1);
		this.a = edge.a;
	}

	public Face getPair(Face face) {
		if (face == f0) {
			return f1;
		}
		if (face == f1) {
			return f0;
		}
		return null;

	}

	public void setF0(Face f0) {
		this.f0 = f0;
	}

	public void setF1(Face f1) {
		this.f1 = f1;
	}

	public Assign getA() {
		return a;
	}

	public Face getF0() {
		return f0;
	}

	public Face getF1() {
		return f1;
	}

}
