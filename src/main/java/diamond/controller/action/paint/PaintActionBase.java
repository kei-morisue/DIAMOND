/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.action.paint.state.PaintStateBase;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class PaintActionBase {
	private PaintStateBase state;

	public PaintActionBase() {
		setInitialState();
	}

	protected abstract void setInitialState();

	protected final void setActionState(PaintStateBase state) {
		this.state = state;
	}

	protected final PaintStateBase getState() {
		return state;
	}

	public PaintActionBase onLeftClick(PaintScreen screen) {
		state = state.doAction(screen);
		return null;
	}

	public PaintActionBase onRightClick(PaintScreen screen) {
		state = state.undoAction(screen);
		return this;
	}

	public void onMove(PaintScreen screen) {
		state.onMove(screen);
	}

	public abstract void onDraw(Graphics2D g2d, PaintScreen screen);
}
