/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import diamond.controller.action.paint.PaintAction;
import diamond.controller.mouse.MouseUtility;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreenAction extends ScreenAction {
	protected PaintScreen screen;

	public PaintScreenAction(PaintScreen screen) {
		super(screen);
		this.screen = screen;
	}

	private PaintAction getPaintAction() {
		return screen.getPaintAction();
	}

	@Override
	public void mouseMoved(
			MouseEvent e) {
		getPaintAction().onMove(screen, e);
		e.getComponent().repaint();
	}

	@Override
	public void mouseWheelMoved(
			MouseWheelEvent e) {
		zoom(e);
	}

	@Override
	public void mouseClicked(
			MouseEvent e) {
		if (MouseUtility.isLeftClick(e)) {
			getPaintAction().onLeftClick(screen, e);
			e.getComponent().repaint();
			return;
		}
		if (MouseUtility.isRightClick(e)) {
			getPaintAction().onRightClick(screen, e);
			e.getComponent().repaint();
			return;
		}
	}

}
