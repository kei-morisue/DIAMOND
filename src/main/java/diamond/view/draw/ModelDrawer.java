/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;

import diamond.model.XY;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.view.draw.shape.SegmentShape;

/**
 * @author Kei Morisue
 *
 */
public class ModelDrawer extends DrawerBase {

	public static double CLIP = 0.9;

	@Override
	public XY getXY(
			Vertex v) {
		return v.d;
	}

	@Override
	public XY[] getXY(
			Crease crease) {
		return super.getXY(crease);
	}

	@Override
	public double getRadius(
			Vertex vertex) {
		return vertex.isPicked() ? 15.0 : 0.0;
	}

	@Override
	protected Shape getShape(
			Crease crease,
			double scale) {
		return SegmentShape.getShape(crease, scale, CLIP, this);
	}

	@Override
	protected Color getColor(
			Face face) {
		return face.isPicked() ? Color.GREEN
				: face.isFlip ? Color.GRAY : Color.WHITE;
	}

	@Override
	protected Color getColor(
			Edge edge) {
		return edge.isPicked() ? Color.GREEN
				: Color.BLACK;
	}

	@Override
	protected BasicStroke getStroke(
			Edge edge,
			double scale) {
		BasicStroke stroke = new BasicStroke((float) (3.0 / scale),
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
