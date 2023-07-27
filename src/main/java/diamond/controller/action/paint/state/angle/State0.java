/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.Graphics2D;
import java.util.HashSet;

import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Vertex;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends PaintStateBase {

	private Vertex vertex;
	private Edge edge;
	private Crease crease;

	@Override
	protected boolean act(
			PaintScreen screen) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		return act(screen);
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		refresh(screen);
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
		refresh(screen);
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

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
	}

	@Override
	protected void refresh(
			PaintScreen screen) {
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

	@Override
	protected PaintStateBase getNextState() {
		// TODO 自動生成されたメソッド・スタブ
		return this;
	}

	@Override
	protected PaintStateBase getPrevState() {
		return this;
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
