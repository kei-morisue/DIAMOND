/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.draw.shape;

import java.awt.BasicStroke;
import java.util.List;

import diamond.model.XY;
import diamond.model.fold.Edge;
import diamond.model.fold.Edge.Assign;
import diamond.model.fold.Fold;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class ShapeProviderFolded extends ShapeProviderBase {
	private List<XY> vfs;
	private List<Vertex> vs;

	public ShapeProviderFolded(Fold fold) {
		super();
		this.vfs = fold.getVfs();
		this.vs = fold.getVertices();
	}

	@Override
	public XY getXY(Vertex v) {
		return vfs.get(vs.indexOf(v));
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
