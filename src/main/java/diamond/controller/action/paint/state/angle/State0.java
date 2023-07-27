/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.angle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends PaintStateBase {

	private Point2D.Double PointerLocation = new Point2D.Double(0.0, 0.0);

	@Override
	protected boolean act(
			PaintScreen screen) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		PointerLocation = p;
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
		// TODO STUB
		double scale = screen.getTransform().getScale();
		double size = 10.0;
		double x = PointerLocation.x;
		double y = PointerLocation.y;
		g2d.setColor(Color.GREEN);
		g2d.fill(new Ellipse2D.Double(x, y, size / scale, size / scale));

	}

	@Override
	protected void refresh(
			PaintScreen screen) {
		PointerLocation = new Point2D.Double(0.0, 0.0);
	}

	@Override
	protected PaintStateBase getNextState() {
		// TODO 自動生成されたメソッド・スタブ
		return this;
	}

	@Override
	protected PaintStateBase getPrevState() {
		// TODO 自動生成されたメソッド・スタブ
		return this;
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
