/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.Collections;
import java.util.HashSet;

import diamond.model.Dir;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Cp extends Flat {
	transient private HashSet<Vertex> vertices = new HashSet<Vertex>();
	transient private HashSet<Edge> edges = new HashSet<Edge>();
	transient private HashSet<Crease> creases = new HashSet<Crease>();

	private Face baseFace;

	public Cp(double scale) {
		super();
		CpBuilder.buildSquare(this, scale);
		this.baseFace = getFaces().get(0);
		fold();
		implyFaceOrder();
		buildVertices();
		buildEdges();
		buildCreases();
	}

	private void buildVertices() {
		faces.forEach(face -> {
			face.getVertices().forEach(vertex -> {
				vertices.add(vertex);
			});
			face.getCreases().forEach(crease -> {
				vertices.add(crease.getV0());
				vertices.add(crease.getV1());
			});
		});
	}

	private void buildEdges() {
		faces.forEach(face -> {
			face.getEdges().forEach(edge -> {
				edges.add(edge);
			});
		});
	}

	private void buildCreases() {
		faces.forEach(face -> {
			face.getCreases().forEach(crease -> {
				creases.add(crease);
			});
		});
	}

	public void fold() {
		clearFolded();
		Edge borderEdge = baseFace.getEdges().get(0);
		buildFolded(baseFace, true, borderEdge);
	}

	public void clearFolded() {
		faces.forEach(face -> {
			face.isFlip = false;
			face.isFolded = false;
			face.getVertices().forEach(v -> {
				v.f = v.p;
			});

		});
	}

	private void buildFolded(
			Face face,
			boolean prevFaceFlip,
			Edge foldedEdge) {
		if (face.isFolded || face == null) {
			return;
		}
		face.isFlip = !prevFaceFlip;
		face.isFolded = true;
		XY v0f = foldedEdge.getV0().f;
		XY v0 = foldedEdge.getV0().p;
		Dir x = foldedEdge.dir();
		Dir xf = foldedEdge.dirF();
		Dir y = x.perp();
		Dir yf = xf.perp();
		face.getVertices().forEach(vertex -> {
			vertex.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
		});
		face.getCreases().forEach(crease -> {
			Vertex w0 = crease.getV0();
			Vertex w1 = crease.getV1();
			w0.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
			w1.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
		});

		face.getEdges().forEach(edge -> {
			buildFolded(edge.getPair(face), !prevFaceFlip, edge);
		});

	}

	public void implyFaceOrder() {
		boolean retry = false;
		for (int i = 0; i < faces.size(); i++) {
			if (swapFaceOrder(faces.get(i))) {
				retry = true;
				break;
			}
		}
		if (retry) {
			implyFaceOrder();
		}
		return;
	}

	public boolean swapFaceOrder(
			Face face) {
		int i = faces.indexOf(face);
		Face fi = faces.get(i);
		boolean flip = fi.isFlip;
		for (Edge edge : fi.getEdges()) {
			Face fj = edge.getPair(fi);
			boolean isValley = edge.isValley();
			if (fj == null) {
				continue;
			}
			int j = faces.indexOf(fj);
			if (!(flip ^ isValley) && i < j
					|| flip ^ isValley && j < i) {
				Collections.swap(faces, i, j);
				return true;
			}
		}
		return false;
	}

	public HashSet<Vertex> getVertices() {
		return vertices;
	}

	public HashSet<Edge> getEdges() {
		return edges;
	}

	public HashSet<Crease> getCreases() {
		return creases;
	}

	public Face getBaseFace() {
		return baseFace;
	}
}
