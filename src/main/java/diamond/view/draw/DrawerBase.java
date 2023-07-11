/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Vertex;
import diamond.view.draw.color.ColorProviderBase;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public abstract class DrawerBase {
	protected ColorProviderBase colorProvider;
	protected ShapeProviderBase shapeProvider;

	public DrawerBase(ColorProviderBase colorProvider, ShapeProviderBase shapeProvider) {
		this.colorProvider = colorProvider;
		this.shapeProvider = shapeProvider;
	}

	abstract protected ArrayList<Vertex> getVertices();

	abstract protected ArrayList<Edge> getEdges();

	abstract protected ArrayList<Face> getFaces();

	private void drawVertices(Graphics2D g2d, double scale) {
		ArrayList<Vertex> vertices = getVertices();
		vertices.forEach(vertex -> {
			Shape s = shapeProvider.getShape(vertex, scale);
			g2d.setColor(colorProvider.getColor(vertex));
			g2d.fill(s);
		});
	}

	private void drawEdges(Graphics2D g2d, double scale) {
		ArrayList<Edge> edges = getEdges();
		edges.forEach(e -> {
			BasicStroke stroke = shapeProvider.getStroke(e, scale);
			g2d.setStroke(stroke);
			Shape s = shapeProvider.getShape(e, scale);
			g2d.setColor(colorProvider.getColor(e));

			g2d.draw(s);

		});
	}

	private void drawFaces(Graphics2D g2d, double scale) {
		ArrayList<Face> faces = getFaces();
		faces.forEach(face -> {
			g2d.setColor(colorProvider.getColor(face));
			Shape path = shapeProvider.getShape(face, scale);
			g2d.fill(path);
		});
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

}
