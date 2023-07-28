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
public abstract class FindingState0Base extends FindingStateBase {
	@Override
	protected PaintStateBase getPrevState() {
		return this;
	}

	@Override
	protected void refresh(
			PaintScreen screen) {
		clearPicked();
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		if (segment == null) {
			return false;
		}
		segment.remove(screen.getCp());
		refresh(screen);
		return true;
	}
}
