/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import diamond.controller.mouse.MouseUtility;
import diamond.view.ui.screen.ScreenBase;

/**
 * @author Kei Morisue
 *
 */
public class ScreenAction implements MouseListener, MouseMotionListener, MouseWheelListener {
	protected ScreenBase screen;
	protected Point2D clicked;

	public ScreenAction(ScreenBase screen) {
		this.screen = screen;
	}

	protected void zoom(MouseWheelEvent e) {
		double zoom = zoomAmount(e);
		screen.zoom(zoom);
	}

	protected double zoomAmount(MouseWheelEvent e) {
		return Math.pow(1.5, -e.getWheelRotation());
	}

	protected void shift(MouseEvent e) {
		Point2D p0 = clicked;
		double dx = e.getX() - p0.getX();
		double dy = e.getY() - p0.getY();
		screen.shift(dx, dy);
	}

	protected void rotate(MouseWheelEvent e) {
		double theta = thetaAmount(e);
		screen.rotate(theta);
	}

	protected double thetaAmount(MouseWheelEvent e) {
		double moved = e.getWheelRotation();
		return Math.PI / 8 * ((moved) % 8);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (MouseUtility.isCtrlDown(e)) {
			rotate(e);
		} else {
			zoom(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		shift(e);
		clicked = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicked = e.getPoint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

}