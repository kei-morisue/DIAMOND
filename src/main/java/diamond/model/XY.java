/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

/**
 * @author Kei Morisue
 *
 */
public class XY {
	private double x;
	private double y;

	public XY(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Dir dir(XY v) {
		return new Dir(v.x - x, v.y - y);
	}

	public XY mid(XY v, double t) {
		return dir(v).mul(t).ver(this);
	}

	public XY mid(XY v) {
		return mid(v, 0.5);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
