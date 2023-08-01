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
import diamond.model.fold.symbol.ArrowMV;
import diamond.model.fold.symbol.Circle;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FindingStateBase {

	static final int ARROW_M = 1;
	static final int ARROW_V = -1;
	static final int ARROW_MV = 0;

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
		Cp cp = screen.getPalette().getCp();
		if (vertex == null) {
			if (segment == null) {
				return true;
			}
			cp.removeSymbol(segment);
			return true;
		}
		cp.removeSymbol(vertex);
		return false;
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		super.findCtrl(screen, p);
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		super.find(screen, p);
	}

	@Override
	protected boolean act(
			PaintScreen screen) {
		if (segment == null) {
			return false;
		}
		Cp cp = screen.getPalette().getCp();
		// TODO put various types of symbol
		cp.put(segment, new ArrowMV(segment));
		return true;
	}

	@Override
	protected boolean actCtrl(
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
