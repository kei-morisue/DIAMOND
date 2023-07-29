/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.flip;

import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingState0Base;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Segment;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FindingState0Base {

	@Override
	protected PaintStateBase getNextState() {
		return this;
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		super.findCtrl(screen, p);
	}

	@Override
	protected boolean act(
			PaintScreen screen) {
		if (segment == null) {
			return false;
		}
		Cp cp = screen.getCp();
		segment.flip();
		cp.fold();
		return true;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		if (segment == null) {
			return false;
		}
		if (segment.getA() == Segment.NONE) {
			return false;
		}
		Cp cp = screen.getCp();
		Segment flip = segment.getFlip();
		segment.remove(cp);
		return flip.add(cp);
	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
	}

}
