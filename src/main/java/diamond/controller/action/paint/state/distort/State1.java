/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.distort;

import diamond.controller.action.paint.state.LazyStatebase;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends LazyStatebase {

	@Override
	protected PaintStateBase getNextState() {
		return new State0();
	}

	@Override
	protected PaintStateBase getPrevState() {
		return new State0();
	}

	@Override
	protected void onSave(
			PaintScreen screen) {

	}

}
