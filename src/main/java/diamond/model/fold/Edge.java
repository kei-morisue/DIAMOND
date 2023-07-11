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
public class Edge extends Renderable implements Serializable {
	public static enum Assign implements Serializable {
		M, V, F, U, B
	};

	private Vertex v0;
	private Vertex v1;
	private Assign a;
	private Face f0;
	private Face f1;

	public Edge(Vertex v0, Vertex v1, Assign a) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.a = a;
	}

	public void setF0(Face f0) {
		this.f0 = f0;
	}

	public boolean isFlipping() {
		return a == Assign.M || a == Assign.V || a == Assign.U;
	}

	public void setF1(Face f1) {
		this.f1 = f1;
	}

	public void setA(Assign a) {
		this.a = a;
	}

	public Vertex getV0() {
		return v0;
	}

	public Vertex getV1() {
		return v1;
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
