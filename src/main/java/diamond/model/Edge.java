/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

/**
 * @author Kei Morisue
 *
 */
public class Edge {
	private Vertex v0;

	private Vertex v1;
	private Assign.Edge a;
	private Face f0;
	private Face f1;

	public Edge(Vertex v0, Vertex v1, Assign.Edge a) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.a = a;
	}

	public void setF0(Face f0) {
		this.f0 = f0;
	}

	public void setF1(Face f1) {
		this.f1 = f1;
	}

	public Vertex getV0() {
		return v0;
	}

	public Vertex getV1() {
		return v1;
	}

	public Assign.Edge getA() {
		return a;
	}

	public Face getF0() {
		return f0;
	}

	public Face getF1() {
		return f1;
	}

}
