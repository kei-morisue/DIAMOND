/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import diamond.controller.action.paint.state.FindingState0Base;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.fold.Crease;
import diamond.model.fold.Face;
import diamond.view.draw.CpDrawer;
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
		if (vertex == null) {

			if (segment == null) {
				return;
			}
			if (!segment.isEdge()) {
				Crease crease = (Crease) segment;
				Face face = crease.getFace();
				g2d.setColor(Color.GREEN);
				g2d.fill(new CpDrawer().getShape(face, screen.getScale()));
			}
			return;

		}
		g2d.setColor(Color.GREEN);
		vertex.getAdj().forEach(v -> {
			Shape s = new Ellipse2D.Double(v.x, v.y, 10, 10);
			g2d.draw(s);
		});
	}

	@Override
	protected PaintStateBase getNextState() {
		return new State1(vertex);
	}

}
