/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

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
		clearFolded();
		Edge borderEdge = baseFace.getEdges().get(0);
		buildFolded(baseFace, true, borderEdge);
	}

	public void clearFolded() {
		vertices.forEach(v -> {
			v.f = v.p;
		});
		faces.forEach(face -> {
			face.isFlip = false;
			face.isFolded = false;
		});
	}

	private void buildFolded(Face face, boolean prevFaceFlip, Edge foldedEdge) {
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
		face.getEdges().forEach(edge -> {
			Vertex vertex = edge.getV0();
			Dir d = v0.dir(vertex.p);
			double cx = x.dot(d) / x.mgSq();
			double cy = y.dot(d) / x.mgSq();
			cy *= (prevFaceFlip) ? 1 : -1;
			vertex.f = xf.mul(cx).add(yf.mul(cy)).ver(v0f);
		});
		face.getEdges().forEach(edge -> {
			buildFolded(edge.getPair(face), !prevFaceFlip, edge);
		});

	}

	@Override
	protected int getMaxFraction() {
		return 300;
	}

}
