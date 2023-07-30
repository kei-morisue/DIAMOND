/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import diamond.model.Dir;
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

	private ArrayList<Vertex> adj = new ArrayList<Vertex>();
	private HashMap<Vertex, Edge> edgesMap = new HashMap<Vertex, Edge>();

	private HashMap<Vertex, Crease> cresesMap = new HashMap<Vertex, Crease>();

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
			boolean prevFaceFlip,
			XY v0f,
			XY v0,
			Dir x,
			Dir xf,
			Dir y,
			Dir yf) {
		Dir d = v0.dir(this);
		double cx = x.dot(d) / x.mgSq();
		double cy = y.dot(d) / x.mgSq();
		cy *= (prevFaceFlip) ? 1 : -1;
		this.f = xf.mul(cx).add(yf.mul(cy)).ver(v0f);
		initD();
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

	public ArrayList<Vertex> getAdj() {
		return adj;
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

	public HashMap<Vertex, Edge> getEdgesMap() {
		return edgesMap;
	}

	public HashMap<Vertex, Crease> getCresesMap() {
		return cresesMap;
	}
}
