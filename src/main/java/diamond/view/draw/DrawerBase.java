/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Renderable;
import diamond.model.fold.Vertex;
import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public abstract class DrawerBase {

	abstract protected ArrayList<Vertex> getVertices();

	abstract protected ArrayList<Edge> getEdges();

	abstract protected ArrayList<Face> getFaces();

	abstract protected ColorProviderBase getColorProvider();

	abstract protected ShapeProviderBase getShapeProvider();

	private void drawVertices(Graphics2D g2d, double scale) {
		ArrayList<Vertex> vertices = getVertices();
		vertices.forEach(vertex -> {
			Shape s = getShape(vertex, scale);
			g2d.setColor(getColor(vertex));
			g2d.fill(s);
		});
	}

	private void drawEdges(Graphics2D g2d, double scale) {
		BasicStroke stroke = new BasicStroke((float) (5.0 / scale), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		g2d.setStroke(stroke);
		ArrayList<Edge> edges = getEdges();
		edges.forEach(e -> {
			Shape s = getShape(e, scale);
			g2d.setColor(getColor(e));
			g2d.draw(s);

		});
	}

	private void drawFaces(Graphics2D g2d, double scale) {
		ArrayList<Face> faces = getFaces();
		faces.forEach(face -> {
			drawFace(g2d, face, scale);
		});
	}

	private void drawFace(Graphics2D g2d, Face face, double scale) {
		g2d.setColor(getColor(face));
		Shape path = getShape(face, scale);
		g2d.fill(path);
	}

	public void draw(Graphics2D g2d) {
		double scale = getScale(g2d);
		drawFaces(g2d, scale);
		drawEdges(g2d, scale);
		drawVertices(g2d, scale);
	}

	private double getScale(Graphics2D g2d) {
		AffineTransform transform = g2d.getTransform();
		double scaleX = transform.getScaleX();
		double scaleY = transform.getScaleY();
		double shearX = transform.getShearX();
		double shearY = transform.getShearY();
		return Math.sqrt(scaleX * scaleY - shearX * shearY);
	}

	private Color getColor(Renderable r) {
		ColorProviderBase colorProvider = getColorProvider();
		return r.getColor(colorProvider);

	}

	private Shape getShape(Renderable r, double scale) {
		ShapeProviderBase shapeProvider = getShapeProvider();
		return r.getShape(shapeProvider, scale);
	}
}
