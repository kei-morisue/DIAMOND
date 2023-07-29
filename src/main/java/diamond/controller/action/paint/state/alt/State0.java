/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.alt;

import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingState0Base;
import diamond.controller.action.paint.state.PaintStateBase;
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
	protected boolean act(
			PaintScreen screen) {

		return false;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
