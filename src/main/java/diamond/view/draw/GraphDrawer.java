/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import diamond.model.XY;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Vertex;
import diamond.view.draw.shape.FaceShape;
import diamond.view.draw.shape.VertexShape;

/**
 * @author Kei Morisue
 *
 */
public class GraphDrawer extends DrawerBase {

	@Override
	protected Color getColor(
			Face face) {
		return Color.MAGENTA;
	}

	@Override
	protected Color getColor(
			Edge edge) {
		return Color.MAGENTA;
	}

	@Override
	protected Color getColor(
			Vertex vertex) {
		return Color.MAGENTA;
	}

	@Override
	protected BasicStroke getStroke(
			Edge edge,
			double scale) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public double getRadius(
			Vertex vertex) {
		return 20;
	}

	@Override
	public void draw(
			Graphics2D g2d,
			Vertex vertex,
			double scale) {
		g2d.setColor(getColor(vertex));
		g2d.setStroke(new BasicStroke((float) (2.0 / scale)));
		double r = getRadius(vertex);
		vertex.forAdj(v -> {
			g2d.draw(VertexShape.getShape(v, r, scale, this));
		});

	}

	@Override
	public void draw(
			Graphics2D g2d,
			Crease crease,
			double scale) {
		Face face = crease.getFace();
		g2d.setColor(getColor(face));
		g2d.fill(FaceShape.getShape(face, scale, 0.75, this));
	}

	@Override
	public void draw(
			Graphics2D g2d,
			Edge edge,
			double scale) {
		Face f0 = edge.getF0();
		g2d.setColor(getColor(f0));
		g2d.fill(FaceShape.getShape(f0, scale, 0.75, this));
		Face f1 = edge.getF1();
		if (f1 != null) {
			g2d.setColor(getColor(f1));
			g2d.fill(FaceShape.getShape(f1, scale, 0.75, this));
		}
	}

	@Override
	public XY getXY(
			Vertex vertex) {
		return vertex;
	}

	@Override
	public double getArrowBodyScale(
			double screenScale) {
		return 0.1 / screenScale;
	}

}
