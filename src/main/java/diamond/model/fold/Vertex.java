/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.awt.Color;
import java.awt.Shape;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import diamond.model.XY;
import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public class Vertex implements Renderable, Serializable {
	private XY v;
	private XY f;
	private XY d;
	private ArrayList<Vertex> adj = new ArrayList<Vertex>();
	private double kawasaki;

	public Vertex(XY v) {
		this.v = v;
	}

	public class AngleComparator implements Comparator<Vertex> {

		@Override
		public int compare(Vertex v1, Vertex v2) {
			double angle1 = v.dir(v1.v).angle();
			double angle2 = v.dir(v2.v).angle();
			return angle1 - angle2 < 0 ? -1 : 1;
		}

	}

	public ArrayList<Vertex> getAdj() {
		return adj;
	}

	public double getKawasaki() {
		return kawasaki;
	}

	public void setKawasaki(double kawasaki) {
		this.kawasaki = kawasaki;
	}

	public XY getD() {
		return d;
	}

	public XY getV() {
		return v;
	}

	public XY getF() {
		return f;
	}

	public void setF(XY f) {
		this.f = f;
	}

	public void setD(XY d) {
		this.d = d;
	}

	@Override
	public Shape getShape(ShapeProviderBase shapeProvider, double scale) {
		return shapeProvider.getShape(this, scale);
	}

	@Override
	public Color getColor(ColorProviderBase colorProvider) {
		return colorProvider.getColor(this);
	}
}
