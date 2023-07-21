/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import diamond.controller.mouse.MouseUtility;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenAction implements MouseMotionListener, MouseListener {
	protected PaintScreen screen;

	public PaintScreenAction(PaintScreen screen) {
		this.screen = screen;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ScreenTransform transform = screen.getTransform();
		screen.mousePoint = MouseUtility.getLogicalPoint(transform, e.getPoint());
		screen.getPaintAction().onMove(screen);
		e.getComponent().repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (MouseUtility.isLeftClick(e)) {
			screen.getPaintAction().onLeftClick(screen);
		}
		if (MouseUtility.isRightClick(e)) {
			screen.getPaintAction().onRightClick(screen);
		}
		e.getComponent().repaint();
		return;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		screen.grabFocus();
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
