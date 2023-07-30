/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import java.util.Set;

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Segment;
import diamond.model.fold.Vertex;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class FindingStateBase extends PaintStateBase {

	protected Vertex vertex;

	protected Segment segment;

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		clearPicked();
		Cp cp = screen.getCp();
		double scale = screen.getScale();
		double epsSq = 100 / scale / scale;
		Vertex found = cp.find(p, epsSq);
		if (found != null) {
			vertex = found;
			found.setPicked(true);
			return;
		}
		vertex = null;
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		clearPicked();
		Cp cp = screen.getCp();
		Set<Segment> segs = cp.getSegments();
		double scale = screen.getScale();
		for (Segment seg : segs) {
			if (seg.distanceSq(p) < 500 / scale / scale) {
				this.segment = seg;
				seg.setPicked(true);
				return;
			}
		}
		segment = null;
	}

	protected void clearPicked() {
		if (vertex != null) {
			vertex.setPicked(false);
		}
		if (segment != null) {
			segment.setPicked(false);
		}
		vertex = null;
		segment = null;
	}

}
