/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.Collections;

import diamond.model.Dir;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Cp extends Flat {
	private Face baseFace;

	public Cp(double scale) {
		super();
		CpBuilder.buildSquare(this, scale);
		this.baseFace = getFaces().get(0);
		fold();
		implyFaceOrder();
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
			setF(vertex, prevFaceFlip, v0f, v0, x, xf, y, yf);
		});
		face.getCreases().forEach(crease -> {
			Vertex w0 = crease.getV0();
			Vertex w1 = crease.getV1();
			setF(w0, prevFaceFlip, v0f, v0, x, xf, y, yf);
			setF(w1, prevFaceFlip, v0f, v0, x, xf, y, yf);
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
			boolean isValley = !edge.isValley();
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

	private void setF(
			Vertex vertex,
			boolean prevFaceFlip,
			XY v0f,
			XY v0,
			Dir x,
			Dir xf,
			Dir y,
			Dir yf) {
		Dir d = v0.dir(vertex.p);
		double cx = x.dot(d) / x.mgSq();
		double cy = y.dot(d) / x.mgSq();
		cy *= (prevFaceFlip) ? 1 : -1;
		vertex.f = xf.mul(cx).add(yf.mul(cy)).ver(v0f);
	}

	@Override
	protected int getMaxFraction() {
		return 300;
	}

}
