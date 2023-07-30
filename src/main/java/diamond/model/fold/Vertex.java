/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends XY implements Serializable, Renderable {
	public XY f;
	public XY d;
	transient private boolean isFlatFoldable;
	transient private boolean isPicked;

	transient private ArrayList<Vertex> adj = new ArrayList<Vertex>();
	transient private HashMap<Vertex, Edge> edgesMap
			= new HashMap<Vertex, Edge>();

	private HashMap<Vertex, Crease> creasesMap = new HashMap<Vertex, Crease>();

	public Vertex(XY v) {
		super(v.x, v.y);
		f = this;
		d = f;
	}

	public Vertex(Vertex v) {
		super(v.x, v.y);
		f = this;
		d = f;
	}

	@Override
	public XY centroid() {
		return this;
	}

	public void setD(
			double d00,
			double d01,
			double d10,
			double d11) {
		d = dir(apply(d00, d01, d10, d11)).ver(f);
	}

	public void initD() {
		d = f;
	}

	public void initF() {
		f = this;
		d = f;
	}

	public void setF(
			XY f) {
		this.f = f;
		initD();
	}

	public boolean isOnEdge() {
		return edgesMap.size() > 0;
	}

	public class AngleComparator implements java.util.Comparator<Vertex> {

		@Override
		public int compare(
				Vertex v1,
				Vertex v2) {
			double angle1 = dir(v1).angle();
			double angle2 = dir(v2).angle();
			return angle1 - angle2 < 0 ? -1 : 1;
		}

	}

	public void forAdj(
			Consumer<? super Vertex> action) {
		adj.forEach(action);
	}

	public Tuple<Vertex> getPair() {
		if (adj.size() != 2) {
			return null;
		}
		return new Tuple<Vertex>(adj.get(0), adj.get(1));
	}

	public void addAdj(
			Vertex v) {
		v.adj.add(this);
		this.adj.add(v);
	}

	public void sortAdj() {
		adj.sort(this.new AngleComparator());
	}

	public Vertex getPrev(
			Vertex vertex) {
		for (int i = 0; i < adj.size(); i++) {
			Vertex vi = adj.get(i);
			if (vertex == vi) {
				int index1 = i == 0 ? adj.size() : i;
				return adj.get(index1 - 1);
			}
		}
		return null;
	}

	@Override
	public boolean isPicked() {
		return isPicked;
	}

	@Override
	public void setPicked(
			boolean picked) {
		isPicked = picked;
	}

	public Edge getEdge(
			Vertex v) {
		return edgesMap.get(v);
	}

	public Crease getCrease(
			Vertex v) {
		return creasesMap.get(v);
	}

	public void putEdge(
			Vertex vertex,
			Edge edge) {
		if (!adj.contains(vertex)) {
			return;
		}
		edgesMap.put(vertex, edge);
	}

	public void putCrease(
			Vertex vertex,
			Crease crease) {
		if (!adj.contains(vertex)) {
			return;
		}
		creasesMap.put(vertex, crease);
	}
}
