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

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.view.draw.shape.FaceShape;
import diamond.view.draw.shape.SegmentShape;
import diamond.view.draw.shape.VertexShape;

/**
 * @author Kei Morisue
 *
 */
public abstract class DrawerBase {

	abstract protected Color getColor(
			Face face);

	protected Color getColor(
			Vertex vertex) {
		return vertex.isPicked() ? Color.GREEN : Color.GRAY;
	};

	abstract protected Color getColor(
			Edge edge);

	protected Color getColor(
			Crease crease) {
		if (crease.isPicked()) {
			return Color.GREEN;
		}
		switch (crease.getA()) {
		case Segment.MOUNTAIN:
			return Color.RED;
		case Segment.VALLEY:
			return Color.BLUE;
		case Segment.NONE:
			return Color.BLACK;
		default:
			return null;
		}
	};

	protected abstract BasicStroke getStroke(
			Edge edge,
			double scale);

	public abstract double getRadius(
			Vertex vertex);

	protected BasicStroke getStroke(
			Crease crease,
			double scale) {
		return SegmentShape.getStroke(crease, scale);
	};

	public abstract XY getXY(
			Vertex vertex);

	public XY[] getXY(
			Edge edge) {
		XY[] res = { getXY(edge.getV0()), getXY(edge.getV1()) };
		return res;
	}

	public XY[] getXY(
			Crease crease) {
		XY[] res = { getXY(crease.getV0()), getXY(crease.getV1()) };
		return res;
	}

	public ArrayList<XY> getXY(
			Face face) {
		ArrayList<XY> res = new ArrayList<XY>();
		face.getVertices().forEach(v -> {
			res.add(getXY(v));
		});
		return res;
	}

	private void drawVertex(
			Graphics2D g2d,
			Vertex vertex,
			double scale) {
		Shape s = getShape(vertex, scale);
		g2d.setColor(getColor(vertex));
		g2d.fill(s);
	}

	private void drawEdge(
			Graphics2D g2d,
			Edge edge,
			double scale) {
		BasicStroke stroke = getStroke(edge, scale);
		Shape s = getShape(edge, scale);
		g2d.setStroke(stroke);
		g2d.setColor(getColor(edge));
		g2d.draw(s);
		drawVertex(g2d, edge.getV0(), scale);
		drawVertex(g2d, edge.getV1(), scale);
	}

	private void drawCrease(
			Graphics2D g2d,
			Crease crease,
			double scale) {
		BasicStroke stroke = getStroke(crease, scale);
		Shape s = getShape(crease, scale);
		g2d.setStroke(stroke);
		g2d.setColor(getColor(crease));
		g2d.draw(s);
		drawVertex(g2d, crease.getV0(), scale);
		drawVertex(g2d, crease.getV1(), scale);
	}

	private void drawFace(
			Graphics2D g2d,
			Face face,
			double scale) {
		g2d.setColor(getColor(face));
		Shape path = getShape(face, scale);
		g2d.fill(path);
	}

	public void draw(
			Graphics2D g2d,
			Cp cp) {
		double scale = getScale(g2d);
		cp.getFaces().forEach(face -> {
			drawFace(g2d, face, scale);
			face.getEdges().forEach(edge -> {
				drawEdge(g2d, edge, scale);
			});
			face.getCreases().forEach(crease -> {
				drawCrease(g2d, crease, scale);
			});

		});
	}

	protected Shape getShape(
			Vertex vertex,
			double scale) {
		return VertexShape.getShape(
				vertex,
				getRadius(vertex),
				scale,
				this);
	}

	protected Shape getShape(
			Edge e,
			double scale) {
		return SegmentShape.getShape(e, scale, this);
	}

	protected Shape getShape(
			Crease crease,
			double scale) {
		return SegmentShape.getShape(crease, scale, this);
	}

	private Shape getShape(
			Face face,
			double scale) {
		return FaceShape.getShape(face, scale, this);
	}

	protected double getScale(
			Graphics2D g2d) {
		AffineTransform transform = g2d.getTransform();
		double scaleX = transform.getScaleX();
		double scaleY = transform.getScaleY();
		double shearX = transform.getShearX();
		double shearY = transform.getShearY();
		return Math.sqrt(scaleX * scaleY - shearX * shearY);
	}

}
