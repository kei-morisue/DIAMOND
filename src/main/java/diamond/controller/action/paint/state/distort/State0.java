/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.distort;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.model.fold.Cp;
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
		return true;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		return false;
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		PointerLocation = p;
		double density = 1.0 / screen.getCp().size();
		double theta = density * PointerLocation.x / screen.getWidth();
		double k = 1 + density * PointerLocation.y / screen.getHeight();

		double s = Math.sin(theta);
		double c = Math.cos(theta);

		Cp cp = screen.getPalette().getCp();
		cp.forVertices(vertex -> {
			vertex.setD(k * c, -s * k, s * k, k * c);
		});
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		this.find(screen, p);

	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {

	}

	@Override
	protected void refresh(
			PaintScreen screen) {
		PointerLocation = new Point2D.Double(0.0, 0.0);
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		Cp cp = screen.getPalette().getCp();
		cp.forVertices(vertex -> {
			vertex.initD();
		});
		screen.getModelScreen().repaint();
		return true;
	}

	@Override
	protected PaintStateBase getNextState() {
		return new State1();
	}

	@Override
	protected PaintStateBase getPrevState() {
		return this;
	}

}
