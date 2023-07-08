/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Kei Morisue
 *
 */
public class Vertex implements Serializable {
	private XY v;
	private XY f;
	private XY d;
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private double kawasaki;

	public Vertex(XY v) {
		this.v = v;
	}

	public class AngleComparator implements Comparator<Vertex> {

		@Override
		public int compare(Vertex v1, Vertex v2) {
			double angle1 = v.dir(v1.v).angle();
			double angle2 = v.dir(v2.v).angle();
			return angle1 - angle2 < 0 ? -1 : 1;
		}

	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public double getKawasaki() {
		return kawasaki;
	}

	public void setKawasaki(double kawasaki) {
		this.kawasaki = kawasaki;
	}

	public XY getD() {
		return d;
	}

	public XY getV() {
		return v;
	}

	public XY getF() {
		return f;
	}

	public void setF(XY f) {
		this.f = f;
	}

	public void setD(XY d) {
		this.d = d;
	}
}
