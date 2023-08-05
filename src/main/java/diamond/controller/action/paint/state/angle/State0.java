/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingState0Base;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.view.draw.CpDrawer;
import diamond.view.draw.shape.FaceShape;
import diamond.view.draw.shape.VertexShape;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FindingState0Base {

	@Override
	protected boolean act(
			PaintScreen screen) {
		if (vertex == null) {
			return false;
		}
		return true;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		return act(screen);
	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
		// STUB for debug
		double scale = screen.getScale();
		CpDrawer drawer = new CpDrawer();
		if (vertex == null) {

			if (segment == null) {
				return;
			}
			if (!segment.isEdge()) {
				Crease crease = (Crease) segment;
				Face face = crease.getFace();
				g2d.setColor(Color.MAGENTA);
				g2d.fill(FaceShape.getShape(face, scale, 0.75, drawer));
			} else {
				Edge edge = (Edge) segment;
				Face f0 = edge.getF0();
				g2d.setColor(Color.MAGENTA);
				g2d.fill(FaceShape.getShape(f0, scale, 0.75, drawer));
				Face f1 = edge.getF1();
				if (f1 != null) {
					g2d.setColor(Color.MAGENTA);
					g2d.fill(FaceShape.getShape(f1, scale, 0.75, drawer));
				}
			}
			return;

		}
		final double r = 20;
		g2d.setColor(Color.MAGENTA);
		g2d.setStroke(new BasicStroke((float) (2.0 / scale)));
		vertex.forAdj(v -> {
			g2d.draw(VertexShape.getShape(v, r, scale, drawer));
		});
	}

	@Override
	protected PaintStateBase getNextState() {
		return new State1(vertex);
	}

	@Override
	protected void onSave(
			PaintScreen screen) {
	}

}
