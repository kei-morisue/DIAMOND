/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.util;

import java.awt.geom.AffineTransform;

/**
 * @author Kei Morisue
 *
 */
public class ScreenTransform extends AffineTransform {
	private double focusX = 0.0;
	private double focusY = 0.0;
	private double zoom = 1.0;
	private double shiftX = 0.0;
	private double shiftY = 0.0;
	private double theta = 0.0;

	public ScreenTransform(int width, int height) {
		this.focus(width, height);
	}

	public ScreenTransform(ScreenTransform transform) {
		this.focusX = transform.focusX;
		this.focusY = transform.focusY;
		this.zoom = transform.zoom;
		this.shiftX = transform.shiftX;
		this.shiftY = transform.shiftY;
		this.theta = transform.theta;
	}

	private void concat() {
		setToIdentity();
		concatenate(AffineTransform.getTranslateInstance(focusX, focusY));
		concatenate(AffineTransform.getScaleInstance(zoom, zoom));
		concatenate(AffineTransform.getTranslateInstance(shiftX, shiftY));
		concatenate(AffineTransform.getRotateInstance(theta));
	}

	public void focus(int width, int height) {
		this.focusX = width >> 1;
		this.focusY = height >> 1;
		this.concat();
	}

	public void shift(double x, double y) {
		this.shiftX += x / zoom;
		this.shiftY += y / zoom;
		this.concat();
	}

	public void zoom(double scale) {
		this.zoom *= scale;
		this.concat();
	}

	public void rotate(double radian) {
		this.theta += radian;
		this.concat();
	}

	public double getScale() {
		return this.zoom;
	}

	public double getTheta() {
		return this.theta;
	}
}
