/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingState0Base;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.view.draw.GraphDrawer;
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
		GraphDrawer gDrawer = new GraphDrawer();
		if (vertex == null) {

			if (segment == null) {
				return;
			}
			segment.accept(gDrawer, g2d, scale);
			return;

		}
		vertex.accept(gDrawer, g2d, scale);
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
