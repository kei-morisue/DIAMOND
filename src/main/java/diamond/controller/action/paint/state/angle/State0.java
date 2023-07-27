/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingStateBase;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FindingStateBase {

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
	}

	@Override
	protected PaintStateBase getNextState() {
		return new State1(vertex);
	}

	@Override
	protected PaintStateBase getPrevState() {
		return this;
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		if (segment == null) {
			return false;
		}
		refresh(screen);
		return true;
	}

	@Override
	protected void refresh(
			PaintScreen screen) {
		clearPicked();

	}

}
