/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexoffsetauto;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends AbstractState {

	@Override
	protected void setNextClass() {
		nextStateClass = State0.class;
	}

	@Override
	protected void setPrevClass() {
		prevStateClass = State0.class;
	}

	@Override
	protected void undo(Context context) {
	}

	@Override
	protected void aftermath(Context context) {
	}

	@Override
	protected boolean act(Context context) {
		return true;
	}

	@Override
	public void setPointer(Context context) {

	}

}
