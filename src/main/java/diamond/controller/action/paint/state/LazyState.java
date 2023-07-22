/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import java.awt.Graphics2D;

import diamond.model.XY;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class LazyState extends PaintStateBase {

	@Override
	protected void setNextState() {
		nextState = this;
	}

	@Override
	protected void setPrevState() {
		prevState = this;
	}

	@Override
	protected boolean undo(PaintScreen screen, XY p) {
		return true;
	}

	@Override
	protected boolean act(PaintScreen screen) {
		return true;
	}

	@Override
	protected boolean actCtrl(PaintScreen screen) {
		return true;
	}

	@Override
	protected void find(PaintScreen screen, XY p) {
	}

	@Override
	protected void findCtrl(PaintScreen screen, XY p) {
	}

	@Override
	protected void drawState(Graphics2D g2d, PaintScreen screen) {
	}

}
