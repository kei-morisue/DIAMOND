/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state.base;

import java.awt.Color;
import java.awt.Graphics2D;

import diamond.controller.action.paint.state.FindingFaceStateBase;
import diamond.controller.action.paint.state.PaintStateBase;
import diamond.model.XY;
import diamond.view.draw.shape.FaceShape;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FindingFaceStateBase {
	private XY point;

	@Override
	protected PaintStateBase getNextState() {
		return this;
	}

	@Override
	protected PaintStateBase getPrevState() {
		return this;
	}

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		super.find(screen, p);
		this.point = p;
	}

	@Override
	protected boolean undo(
			PaintScreen screen,
			XY p) {
		return false;
	}

	@Override
	protected boolean act(
			PaintScreen screen) {
		if (face == null) {
			return false;
		}
		screen.getCp().setBaseFace(face);
		return true;
	}

	@Override
	protected boolean actCtrl(
			PaintScreen screen) {
		// TODO implement other function
		return act(screen);
	}

	@Override
	protected void drawState(
			Graphics2D g2d,
			PaintScreen screen) {
		g2d.setColor(Color.MAGENTA);
		double scale = screen.getScale();
		g2d.fill(FaceShape.getBaseFaceSymbol(point, scale));
	}

	@Override
	protected void refresh(
			PaintScreen screen) {
		clearPicked();
	}

}
