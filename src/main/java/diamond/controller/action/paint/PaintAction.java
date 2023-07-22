/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import diamond.controller.action.paint.state.PaintStateBase;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class PaintAction {
	protected PaintStateBase state;

	public PaintAction(PaintStateBase initialState) {
		this.state = initialState;
	}

	public PaintAction onLeftClick(PaintScreen screen, MouseEvent e) {
		this.state = state.doAction(screen, e);
		return null;
	}

	public PaintAction onRightClick(PaintScreen screen, MouseEvent e) {
		this.state = state.undoAction(screen, e);
		return this;
	}

	public void onMove(PaintScreen screen, MouseEvent e) {
		state.onMove(screen, e);
	}

	public void onDraw(Graphics2D g2d, PaintScreen screen) {
		state.onDraw(g2d, screen);
	};
}
