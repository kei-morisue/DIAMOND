/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class CpDrawer extends DrawerBase {
	@Override
	public void draw(
			Graphics2D g2d,
			Cp cp) {
		super.draw(g2d, cp);
		drawBaseFaceSymbol(g2d, cp);
	}

	private void drawBaseFaceSymbol(
			Graphics2D g2d,
			Cp cp) {
		Face baseFace = cp.getBaseFace();
		XY c = baseFace.centroid();
		g2d.setColor(Color.MAGENTA);
		double scale = getScale(g2d);
		double r = 10 / scale;
		Shape point = new Ellipse2D.Double(c.x - r / 2, c.y - r / 2, r, r);
		g2d.fill(point);
	}

	@Override
	protected XY getXY(
			Vertex v) {
		return v;
	}

	@Override
	public double getRadius(
			Vertex vertex) {
		return vertex.isPicked() ? 15.0 : 0.0;
	}

	@Override
	protected Color getColor(
			Face face) {
		return face.isPicked() ? Color.GREEN : Color.WHITE;
	}

	@Override
	protected Color getColor(
			Edge edge) {
		if (edge.isPicked()) {
			return Color.GREEN;
		}
		switch (edge.getA()) {
		case Edge.MOUNTAIN:
			return Color.RED;
		case Edge.VALLEY:
			return Color.BLUE;
		case Edge.NONE:
			return Color.BLACK;
		default:
			return null;
		}
	}

	@Override
	protected Color getColor(
			Crease crease) {
		if (crease.isPicked() || crease.getA() != Segment.NONE) {
			return super.getColor(crease);
		}
		return Color.LIGHT_GRAY;
	}

	@Override
	protected BasicStroke getStroke(
			Edge edge,
			double scale) {
		BasicStroke stroke = new BasicStroke((float) (1.0 / scale),
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		return stroke;
	}

	@Override
	protected BasicStroke getStroke(
			Crease crease,
			double scale) {
		if (crease.getA() == Segment.NONE) {
			BasicStroke stroke = new BasicStroke((float) (0.0 / scale),
					BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
			return stroke;

		}
		BasicStroke stroke = new BasicStroke((float) (2.0 / scale),
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER,
				2,
				new float[] { (float) (10.0 / scale), (float) (10.0f / scale) },
				0.0f);
		return stroke;

	}

}
