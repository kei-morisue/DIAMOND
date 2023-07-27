/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import java.util.HashSet;

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class FindingStateBase extends PaintStateBase {

	protected Vertex vertex;
	protected Edge edge;
	protected Crease crease;

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		clearPicked();
		Cp cp = screen.getCp();
		HashSet<Vertex> vertices = cp.getVertices();
		for (Vertex v : vertices) {
			double scale = screen.getScale();
			if (v.p.distanceSq(p) < 100 / scale / scale) {
				vertex = v;
				v.isPicked = true;
				return;
			}
		}
		vertex = null;
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		clearPicked();
		Cp cp = screen.getCp();
		HashSet<Edge> edges = cp.getEdges();
		HashSet<Crease> creases = cp.getCreases();
		double scale = screen.getScale();
		for (Edge edge : edges) {
			if (edge.distanceSq(p) < 500 / scale / scale) {
				this.edge = edge;
				edge.isPicked = true;
				return;
			}
		}
		for (Crease crease : creases) {
			if (crease.distanceSq(p) < 500 / scale / scale) {
				this.crease = crease;
				crease.isPicked = true;
				return;
			}
		}
		vertex = null;

	}

	protected Segment getSegment() {
		if (edge == null) {
			if (crease == null) {
				return null;
			}
			return crease;
		}
		return edge;
	}

	protected void clearPicked() {
		if (vertex != null) {
			vertex.isPicked = false;
		}
		if (edge != null) {
			edge.isPicked = false;
		}
		if (crease != null) {
			crease.isPicked = false;
		}
		vertex = null;
		edge = null;
		crease = null;
	}

}
