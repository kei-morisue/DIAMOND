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
	private XY v;
	private ArrayList<Vertex> adj = new ArrayList<Vertex>();

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

	public ArrayList<Vertex> getAdj() {
		return adj;
	}

	public XY getV() {
		return v;
	}

}
