/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.view.draw.shape.FaceShape;

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
		drawBaseFaceSymbol(g2d, cp.getBaseFace(), getScale(g2d));
	}

	public void drawBaseFaceSymbol(
			Graphics2D g2d,
			Face baseFace,
			double scale) {
		g2d.setColor(Color.MAGENTA);
		Shape s = FaceShape.getBaseFaceSymbol(baseFace, scale);
		g2d.fill(s);
	}

	@Override
	public XY getXY(
			Vertex v) {
		return v;
	}

	@Override
	public double getRadius(
			Vertex vertex) {
		return (vertex.isPicked() || !vertex.isFoldable()) ? 15.0 : 5.0;
	}

	@Override
	protected Color getColor(
			Vertex vertex) {
		return vertex.isPicked() ? Color.GREEN
				: vertex.isFoldable() ? Color.BLACK : Color.MAGENTA;
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
		return Color.ORANGE;
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
	public double getArrowBodyScale(
			double screenScale) {
		return 0.25 / screenScale;
	}

}
