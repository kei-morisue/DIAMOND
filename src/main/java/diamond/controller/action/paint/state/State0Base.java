/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import diamond.model.XY;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class State0Base extends PaintStateBase {

	@Override
	protected void setPrevState() {
		prevState = this;

	}

	@Override
	protected boolean undo(PaintScreen screen, XY p) {
		// TODO remove selected line
		return false;
	}

}
