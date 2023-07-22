/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import java.util.ArrayList;

import diamond.controller.action.Palette;
import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Edge;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class State0Base extends PaintStateBase {

	@Override
	protected void setPrevState() {
		prevState = this;

	}

	@Override
	protected boolean undo(PaintScreen screen, XY p) {
		Palette palette = screen.getPalette();
		double scale = screen.getTransform().getScale();
		Cp cp = palette.getCp();
		ArrayList<Edge> edges = cp.getEdges();
		for (Edge edge : edges) {
			XY c = edge.centroid();
			if (c.distanceSq(p) < 30.0 / scale) {

			}
		}
		return false;
	}

}
