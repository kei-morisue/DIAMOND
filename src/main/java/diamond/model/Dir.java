/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

/**
 * @author Kei Morisue
 *
 */
public class Dir {
	private double x;
	private double y;

	public Dir(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public XY ver(XY v0) {
		return new XY(v0.getX() + x, v0.getY() + y);
	}

	public Dir mul(double s) {
		return new Dir(x * s, y * s);
	}

	public double dot(Dir d) {
		return x * d.x + y * d.y;
	}

	public double cross(Dir d) {
		return x * d.y - y * d.x;
	}

	public Dir unit() {
		return mul(mg());
	}

	public double angle() {
		double angle = Math.atan2(y, x);
		return angle + angle < 0 ? 2 * Math.PI : 0;
	}

	public double mg() {
		return Math.sqrt(mgSq());
	}

	public double mgSq() {
		return dot(this);
	}

	public Dir div(double d) {
		return mul(1.0 / d);
	}

	public Dir perp() {
		return new Dir(y, -x);
	}

}
