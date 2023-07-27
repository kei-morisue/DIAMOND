/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;

import diamond.model.XY;
import diamond.model.fold.Crease;
import diamond.model.fold.Edge;
import diamond.model.fold.Face;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class ModelDrawer extends DrawerBase {

	@Override
	protected XY getXY(
			Vertex v) {
		return v.d;
	}

	@Override
	protected XY[] getXY(
			Edge edge) {
		XY[] res = { getXY(edge.getV0()), getXY(edge.getV1()) };
		return res;
	}

	@Override
	protected XY[] getXY(
			Crease crease) {
		XY[] res = { getXY(crease.getV0()), getXY(crease.getV1()) };
		return res;
	}

	@Override
	protected ArrayList<XY> getXY(
			Face face) {
		ArrayList<XY> res = new ArrayList<XY>();
		face.getVertices().forEach(v -> {
			res.add(getXY(v));
		});
		return res;
	}

	@Override
	public double getRadius(
			Vertex vertex) {
		return vertex.isPicked ? 15.0 : 0.0;
	}

	@Override
	protected Color getColor(
			Face face) {
		return face.isPicked ? Color.GREEN
				: face.isFlip ? Color.GRAY : Color.WHITE;
	}

	@Override
	protected Color getColor(
			Vertex vertex) {
		return vertex.isPicked ? Color.GREEN : Color.WHITE;
	}

	@Override
	protected Color getColor(
			Edge edge) {
		return edge.isPicked ? Color.GREEN
				: Color.BLACK;
	}

	@Override
	protected Color getColor(
			Crease crease) {
		return crease.isPicked ? Color.GREEN
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
		BasicStroke stroke = new BasicStroke((float) (1.0 / scale),
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		return stroke;
	}

}
