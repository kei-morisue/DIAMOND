/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import diamond.model.Assign;
import diamond.model.Edge;
import diamond.model.Face;
import diamond.model.Vertex;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public abstract class DrawerBase {
	abstract protected XY getXY(Vertex v);

	abstract protected ArrayList<Vertex> getVertices();

	abstract protected ArrayList<Edge> getEdges();

	abstract protected ArrayList<Face> getFaces();

	private void drawVertices(Graphics2D g2d, double scale) {
		g2d.setColor(Color.BLACK);
		double r = 10 / scale;
		ArrayList<Vertex> vertices = getVertices();
		vertices.forEach(v -> {
			XY xy = getXY(v);
			double x = xy.getX();
			double y = xy.getY();
			g2d.fill(new Ellipse2D.Double(x - r / 2, y - r / 2, r, r));
		});
	}

	private void drawEdges(Graphics2D g2d, double scale) {
		BasicStroke stroke = new BasicStroke((float) (5.0 / scale), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
		g2d.setStroke(stroke);
		ArrayList<Edge> edges = getEdges();
		edges.forEach(e -> {
			XY v1 = getXY(e.getV0());
			XY v2 = getXY(e.getV1());
			double x1 = v1.getX();
			double y1 = v1.getY();
			double x2 = v2.getX();
			double y2 = v2.getY();

			Assign.Edge assign = e.getA();
			switch (assign) {
			case M:
				g2d.setColor(Color.RED);
			default:
				g2d.setColor(Color.WHITE);

				break;
			}

			g2d.draw(new Line2D.Double(x1, y1, x2, y2));

		});
	}

	private void drawFaces(Graphics2D g2d) {
		g2d.setColor(Color.DARK_GRAY);
		ArrayList<Face> faces = getFaces();
		if (faces == null) {
			return;
		}
		faces.forEach(f -> {
			GeneralPath path = null;
			ArrayList<Vertex> vertices = f.getVertices();
			for (Vertex vertex : vertices) {
				XY xy = getXY(vertex);
				if (path == null) {
					path = new GeneralPath();
					path.moveTo(xy.getX(), xy.getY());
				} else {
					path.lineTo(xy.getX(), xy.getY());
				}

			}
			path.closePath();
			g2d.fill(path);
		});
	}

	public void draw(Graphics2D g2d) {
		double scale = getScale(g2d);
		drawFaces(g2d);
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
