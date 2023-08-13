/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import diamond.model.Tuple;
import diamond.model.XY;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class Vertex extends XY implements Serializable, Renderable {
	public XY f;
	public XY d;
	private boolean isFoldable = false;
	transient private boolean isPicked = false;

	transient private ArrayList<Vertex> adj = new ArrayList<Vertex>();
	transient private HashMap<Vertex, Edge> edgesMap
			= new HashMap<Vertex, Edge>();

	transient private HashMap<Vertex, Crease> creasesMap
			= new HashMap<Vertex, Crease>();

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

	public void setFoldable() {
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for (Vertex v : adj) {
			Edge edge = edgesMap.get(v);
			if (edge != null) {
				edges.add(edge);
			}
		}
		this.isFoldable = CpFolder.isFoldable(this, edges);
	}

	public boolean isFoldable() {
		return isFoldable;
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

	public void accept(
			DrawerBase drawer,
			Graphics2D g2d,
			double scale) {
		drawer.draw(g2d, this, scale);
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

	public void initAdj() {
		this.adj = new ArrayList<Vertex>();
		edgesMap
				= new HashMap<Vertex, Edge>();
		creasesMap
				= new HashMap<Vertex, Crease>();
	}

	public boolean isOnEdge() {
		return edgesMap.size() > 0;
	}

	public Face getTopFace(
			List<Face> faces) {
		int top = 0;
		Collection<Edge> edges = edgesMap.values();
		if (!isOnEdge()) {
			return creasesMap.values().iterator().next().getFace();
		}
		for (Edge edge : edges) {
			int i0 = faces.indexOf(edge.getF0());
			int i1 = faces.indexOf(edge.getF1());
			top = Math.max(top, Math.max(i0, i1));
		}
		return faces.get(top);
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

	public static void put(
			Edge edge) {
		Vertex v0 = edge.getV0();
		Vertex v1 = edge.getV1();
		v0.adj.add(v1);
		v1.adj.add(v0);
		v0.edgesMap.put(v1, edge);
		v1.edgesMap.put(v0, edge);
	}

	public static void put(
			Crease crease) {
		Vertex v0 = crease.getV0();
		Vertex v1 = crease.getV1();
		v0.adj.add(v1);
		v1.adj.add(v0);
		v0.creasesMap.put(v1, crease);
		v1.creasesMap.put(v0, crease);
	}
}
