/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.util.ArrayList;

import diamond.model.Edge;
import diamond.model.Face;
import diamond.model.Fold;
import diamond.model.Vertex;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class DrawerFlat extends DrawerBase {

	private Fold fold;

	public DrawerFlat(Fold fold) {
		super();
		this.fold = fold;
	}

	@Override
	protected XY getXY(Vertex v) {
		return v.getV();
	}

	@Override
	protected ArrayList<Vertex> getVertices() {
		return fold.getVertices();
	}

	@Override
	protected ArrayList<Edge> getEdges() {
		return fold.getEdges();
	}

	@Override
	protected ArrayList<Face> getFaces() {
		return fold.getFaces();
	}

}
