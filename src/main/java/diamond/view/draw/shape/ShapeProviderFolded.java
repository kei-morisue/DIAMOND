/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.BasicStroke;

import diamond.model.XY;
import diamond.model.fold.Edge;
import diamond.model.fold.Edge.Assign;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class ShapeProviderFolded extends ShapeProviderBase {

	@Override
	public XY getXY(Vertex v) {
		return v.getF();
	}

	@Override
	public BasicStroke getStroke(Edge e, double scale) {
		if (e.getA() == Assign.F) {
			return new BasicStroke((float) (0.0 / scale), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		}
		return new BasicStroke((float) (1.0 / scale), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	}

	@Override
	public double getRadius(Vertex vertex) {
		return vertex.picked ? 15.0 : 0.0;
	}
}
