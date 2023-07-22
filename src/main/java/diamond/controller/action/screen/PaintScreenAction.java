/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import diamond.controller.action.paint.PaintAction;
import diamond.controller.mouse.MouseUtility;
import diamond.view.ui.screen.PaintScreen;

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

	private PaintAction getPaintAction() {
		return screen.getPaintAction();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		getPaintAction().onMove(screen, e);
		e.getComponent().repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (MouseUtility.isLeftClick(e)) {
			getPaintAction().onLeftClick(screen, e);
		}
		getPaintAction().onRightClick(screen, e);
		if (MouseUtility.isRightClick(e)) {
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
		// TODO 自動生成されたメソッド・スタブ

	}

}
