/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.Collection;

import diamond.model.Geo;

/**
 * @author Kei Morisue
 *
 */
public class Crease extends Segment implements Serializable {

	private Face face;
	public boolean onEdge0 = false;
	public boolean onEdge1 = false;

	public Crease(Vertex v0, Vertex v1, int a) {
		super(v0, v1, a);
	}

	public Crease(Crease crease) {
		super(crease.v0, crease.v1, crease.a);
	}

	public void setFace(
			Face face) {
		this.face = face;
		face.getEdges().forEach(edge -> {
			Vertex a = edge.getV0();
			Vertex b = edge.getV1();
			double lenSq = edge.dir().mgSq();
			if (Geo.on(a, b, v0, lenSq / 900)) {
				onEdge0 = true;
				return;
			}
			if (Geo.on(a, b, v1, lenSq / 900)) {
				onEdge1 = true;
				return;
			}
		});
	}

	public Face getFace() {
		return face;
	}

	@Override
	public void add(
			Vertex v0,
			Vertex v1,
			Collection<Edge> edges,
			Collection<Crease> creases) {
		creases.add(new Crease(v0, v1, a));
	}

	@Override
	public boolean isEdge() {
		return false;
	}

}
