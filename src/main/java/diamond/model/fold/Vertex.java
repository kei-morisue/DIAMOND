/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends Renderable implements Serializable {
	public XY p;
	public XY f;
	public XY d;

	private ArrayList<Vertex> adj = new ArrayList<Vertex>();

	public Vertex(XY v) {
		this.p = v;
	}

	public Vertex(Vertex v) {
		this.p = v.p;
	}

	@Override
	public XY centroid() {
		return p;
	}

	public class AngleComparator implements Comparator<Vertex> {

		@Override
		public int compare(Vertex v1, Vertex v2) {
			double angle1 = p.dir(v1.p).angle();
			double angle2 = p.dir(v2.p).angle();
			return angle1 - angle2 < 0 ? -1 : 1;
		}

	}

	public ArrayList<Vertex> getAdj() {
		return adj;
	}

}
