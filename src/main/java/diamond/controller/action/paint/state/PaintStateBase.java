/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import java.awt.Graphics2D;

import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class PaintStateBase {
	protected PaintStateBase nextState;
	protected PaintStateBase prevState;

	public PaintStateBase() {
		initialize();
	}

	protected void initialize() {
		setNextState();
		setPrevState();
	};

	abstract protected void setNextState();

	abstract protected void setPrevState();

	protected abstract void undo(PaintScreen screen);

	protected abstract void aftermath(PaintScreen screen);

	protected abstract boolean act(PaintScreen screen);

	abstract public void onMove(PaintScreen screen);

	public abstract void onDraw(Graphics2D g2d, PaintScreen screen);

	public final PaintStateBase doAction(PaintScreen screen) {
		if (!act(screen)) {
			return this;
		}
		aftermath(screen);
		return nextState;
	}

	public final PaintStateBase undoAction(PaintScreen screen) {
		undo(screen);
		return prevState;
	}

}