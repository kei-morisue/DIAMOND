/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class PaintStateBase {
	protected Class<? extends PaintStateBase> nextStateClass;
	protected Class<? extends PaintStateBase> prevStateClass;

	public PaintStateBase() {
		initialize();
	}

	protected void initialize() {
		setNextClass();
		setPrevClass();
	};

	abstract protected void setNextClass();

	abstract protected void setPrevClass();

	protected abstract void undo(PaintScreen screen);

	protected abstract void aftermath(PaintScreen screen);

	protected abstract boolean act(PaintScreen screen);

	abstract public void onMove(PaintScreen screen);

	public final PaintStateBase doAction(PaintScreen screen) {
		if (!act(screen)) {
			return this;
		}
		aftermath(screen);
		PaintStateBase nextState = getNextState();
		return nextState;
	}

	public final PaintStateBase undoAction(PaintScreen screen) {
		undo(screen);
		PaintStateBase prevState = getPreviousState();
		return prevState;
	}

	private final PaintStateBase getNextState() {
		return createInstance(this.nextStateClass);
	}

	private final PaintStateBase getPreviousState() {
		return createInstance(this.prevStateClass);
	}

	private final PaintStateBase createInstance(Class<? extends PaintStateBase> c) {
		try {
			return c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return this;
		}
	}
}