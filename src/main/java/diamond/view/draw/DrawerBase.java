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
import java.util.HashMap;
import java.util.HashSet;

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.model.fold.symbol.ArrowMV;
import diamond.model.fold.symbol.Circle;
import diamond.model.fold.symbol.SymbolBase;
import diamond.view.draw.shape.FaceShape;
import diamond.view.draw.shape.SegmentShape;
import diamond.view.draw.shape.SymbolShape;
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

	public final XY[] getXY(
			Edge edge) {
		XY[] res = { getXY(edge.getV0()), getXY(edge.getV1()) };
		return res;
	}

	public final XY[] getXY(
			Crease crease) {
		XY[] res = { getXY(crease.getV0()), getXY(crease.getV1()) };
		return res;
	}

	public final ArrayList<XY> getXY(
			Face face) {
		ArrayList<XY> res = new ArrayList<XY>();
		face.forVertices(v -> {
			res.add(getXY(v));
		});
		return res;
	}

	public final void draw(
			Graphics2D g2d,
			Vertex vertex,
			double scale) {
		Shape s = VertexShape.getShape(
				vertex,
				getRadius(vertex),
				scale,
				this);
		g2d.setColor(getColor(vertex));
		g2d.fill(s);
	}

	public final void draw(
			Graphics2D g2d,
			Edge edge,
			double scale) {
		BasicStroke stroke = getStroke(edge, scale);
		Shape s = SegmentShape.getShape(edge, scale, this);
		g2d.setStroke(stroke);
		g2d.setColor(getColor(edge));
		g2d.draw(s);
	}

	public final void draw(
			Graphics2D g2d,
			Crease crease,
			double scale) {
		BasicStroke stroke = getStroke(crease, scale);
		Shape s = getShape(crease, scale);
		g2d.setStroke(stroke);
		g2d.setColor(getColor(crease));
		g2d.draw(s);
	}

	protected Shape getShape(
			Crease crease,
			double scale) {
		return SegmentShape.getShape(crease, scale, this);
	}

	public final void draw(
			Graphics2D g2d,
			Face face,
			double scale) {
		g2d.setColor(getColor(face));
		Shape path = FaceShape.getShape(face, scale, this);
		g2d.fill(path);
	}

	public void draw(
			Graphics2D g2d,
			Cp cp) {
		double scale = getScale(g2d);
		HashMap<Face, HashSet<SymbolBase>> symbolMap = cp.getSymbolMap();
		cp.forFaces(face -> {
			HashSet<SymbolBase> symbols = symbolMap.get(face);
			face.accept(this, g2d, scale, symbols);
		});
	}

	public final void draw(
			Graphics2D g2d,
			Circle circle,
			double scale) {
		g2d.setColor(Color.MAGENTA);
		g2d.setStroke(new BasicStroke((float) (2 / scale)));
		Shape s = SymbolShape.getShape(circle, scale, this);
		g2d.draw(s);

	}

	public abstract double getArrowBodyScale(
			double screenScale);

	public final void drawBody(
			Graphics2D g2d,
			ArrowMV arrowMV,
			double scale) {
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke((float) (3 / scale)));
		Shape shape = SymbolShape.getBodyShape(arrowMV, scale, this);
		g2d.draw(shape);
	}

	public final void drawHead(
			Graphics2D g2d,
			ArrowMV arrowMV,
			double scale,
			boolean isMountain) {
		g2d.setStroke(new BasicStroke((float) (3 / scale)));
		Shape shape = SymbolShape.getHeadShape(arrowMV, scale, this);
		g2d.setColor(isMountain ? Color.WHITE : Color.BLACK);
		g2d.fill(shape);
		g2d.setColor(Color.BLACK);
		g2d.draw(shape);
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
