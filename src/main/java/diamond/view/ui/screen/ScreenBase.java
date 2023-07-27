/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import diamond.controller.action.screen.ScreenAction;
import diamond.view.util.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public abstract class ScreenBase extends JPanel {
	protected ScreenTransform transform;
	BufferedImage bufferImage;
	Graphics2D g2d;

	public ScreenTransform getTransform() {
		return transform;
	}

	public double getScale() {
		return transform.getScale();
	}

	abstract public void drawComponents(
			Graphics2D g2d);

	public ScreenBase() {
		this.transform = new ScreenTransform(getWidth(), getHeight());
		ScreenAction screenAction = new ScreenAction(this);
		addMouseListener(screenAction);
		addMouseMotionListener(screenAction);
		addMouseWheelListener(screenAction);
	}

	@Override
	public void paintComponent(
			Graphics g) {
		super.paintComponent(g);
		bufferImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) bufferImage.getGraphics();

		drawBackGround(g2d, Color.lightGray);
		drawComponents(g2d);

		g.drawImage(bufferImage, 0, 0, this);
	}

	protected void drawBackGround(
			Graphics2D g2d,
			Color color) {
		g2d.setColor(color);
		int width = getWidth();
		int height = getHeight();
		g2d.fillRect(0, 0, width, height);
		transform.focus(getWidth(), getHeight());
		g2d.setTransform(transform);
	}

	public void zoom(
			double zoom) {
		transform.zoom(zoom);
		repaint();
	}

	public void shift(
			double dx,
			double dy) {
		transform.shift(dx, dy);
		repaint();
	}

	public void rotate(
			double theta) {
		transform.rotate(theta);
		repaint();
	}
}
