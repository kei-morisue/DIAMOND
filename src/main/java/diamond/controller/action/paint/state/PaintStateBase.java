/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import diamond.controller.mouse.MouseUtility;
import diamond.model.XY;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public abstract class PaintStateBase {

	abstract protected PaintStateBase getNextState();

	abstract protected PaintStateBase getPrevState();

	protected abstract boolean undo(
			PaintScreen screen,
			XY p);

	protected abstract boolean act(
			PaintScreen screen);

	protected abstract boolean actCtrl(
			PaintScreen screen);

	protected abstract void find(
			PaintScreen screen,
			XY p);

	protected abstract void findCtrl(
			PaintScreen screen,
			XY p);

	protected abstract void drawState(
			Graphics2D g2d,
			PaintScreen screen);

	protected abstract void refresh(
			PaintScreen screen);

	protected abstract void onSave(
			PaintScreen screen);

	public void onDraw(
			Graphics2D g2d,
			PaintScreen screen) {
		drawState(g2d, screen);
	}

	public void onRefresh(
			PaintScreen screen) {
		refresh(screen);
	}

	public void onMove(
			PaintScreen screen,
			MouseEvent e) {
		XY logicalPoint = getLogicalPoint(screen, e);

		if (MouseUtility.isCtrlDown(e)) {
			findCtrl(screen, logicalPoint);
		} else {
			find(screen, logicalPoint);
		}
	}

	protected void save(
			PaintScreen screen) {
		screen.save();
	}

	private XY getLogicalPoint(
			PaintScreen screen,
			MouseEvent e) {
		ScreenTransform transform = screen.getTransform();
		XY logicalPoint = MouseUtility.getLogicalPoint(transform, e.getPoint());
		return logicalPoint;
	}

	public final PaintStateBase doAction(
			PaintScreen screen,
			MouseEvent e) {
		if (MouseUtility.isCtrlDown(e)) {
			if (!actCtrl(screen)) {
				return this;
			}
		}
		if (!act(screen)) {
			return this;
		}
		PaintStateBase next = getNextState();
		refresh(screen);
		onSave(screen);
		return next;
	}

	public final PaintStateBase undoAction(
			PaintScreen screen,
			MouseEvent e) {
		if (!undo(screen, getLogicalPoint(screen, e))) {
			return this;
		}
		return getPrevState();
	}

}