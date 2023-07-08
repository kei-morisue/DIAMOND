/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import diamond.controller.action.ScreenAction;
import diamond.view.draw.DrawerBase;
import diamond.view.util.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class ScreenBase extends JPanel {
	protected ScreenTransform transform;
	private DrawerBase drawer;

	public ScreenBase(DrawerBase drawer) {
		this.drawer = drawer;
		this.transform = new ScreenTransform(getWidth(), getHeight());
		ScreenAction screenAction = new ScreenAction(this);
		addMouseListener(screenAction);
		addMouseMotionListener(screenAction);
		addMouseWheelListener(screenAction);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		drawBackGround(g2d, Color.lightGray);
		drawer.draw(g2d);
	}

	protected void drawBackGround(Graphics2D g2d, Color color) {
		g2d.setColor(color);
		int width = getWidth();
		int height = getHeight();
		g2d.fillRect(0, 0, width, height);
		transform.focus(getWidth(), getHeight());
		g2d.setTransform(transform);
	}

	public void zoom(double zoom) {
		transform.zoom(zoom);
		repaint();
	}

	public void shift(double dx, double dy) {
		transform.shift(dx, dy);
		repaint();
	}

	public void rotate(double theta) {
		transform.rotate(theta);
		repaint();
	}
}
