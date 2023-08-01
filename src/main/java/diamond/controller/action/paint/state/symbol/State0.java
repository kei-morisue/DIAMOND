/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.symbol;

import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingStateBase;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.symbol.Circle;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FindingStateBase {

	@Override
	protected PaintStateBase getNextState() {
		return this;
	}

	@Override
	protected PaintStateBase getPrevState() {
		return this;
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected boolean act(
			PaintScreen screen) {
		if (vertex == null) {
			return false;
		}
		Cp cp = screen.getPalette().getCp();
		// TODO put various types of symbol
		cp.put(vertex, new Circle(vertex));
		return true;
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

	@Override
	protected void refresh(
			PaintScreen screen) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
