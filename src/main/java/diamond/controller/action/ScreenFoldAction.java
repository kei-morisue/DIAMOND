/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import diamond.controller.mouse.MouseUtility;
import diamond.model.XY;
import diamond.view.ui.screen.ScreenFold;
import diamond.view.util.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class ScreenFoldAction implements MouseMotionListener, MouseListener, KeyListener {
	protected ScreenFold screen;
	public int inputmode = 0;
	public static final int V = 0;
	public static final int E = 1;
	public static final int F = 2;

	public ScreenFoldAction(ScreenFold screen) {
		this.screen = screen;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		ScreenTransform transform = screen.getTransform();
		Point mousePoint = e.getPoint();
		XY xy = MouseUtility.getLogicalPoint(transform, mousePoint);
		double scale = transform.getScale();
		if (inputmode == V) {
			screen.pickVertex(xy, scale);
		}
		screen.repaint();
		screen.getLinkedScreens().forEach(s -> {
			s.repaint();
		});

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isControlDown()) {
			inputmode = E;
			screen.clearPicked();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		inputmode = V;
		screen.clearPicked();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

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
