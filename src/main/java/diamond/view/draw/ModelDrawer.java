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
import diamond.model.fold.Vertex;
import diamond.view.draw.shape.SegmentShape;

/**
 * @author Kei Morisue
 *
 */
public class ModelDrawer extends DrawerBase {

	public static int CLIP = 90;

	private static double clip() {
		return CLIP / 100.0;
	}

	@Override
	public XY getXY(
			Vertex v) {
		return v.d;
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
		return SegmentShape.getShape(crease, scale, clip(), this);
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
	public double getArrowBodyScale(
			double ScreenScale) {
		return 1.0;
	}

}
