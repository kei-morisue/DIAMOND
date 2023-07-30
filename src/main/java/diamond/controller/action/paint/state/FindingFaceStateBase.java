/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint.state;

import diamond.model.XY;
import diamond.model.fold.Cp;
import diamond.model.fold.Face;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class FindingFaceStateBase extends PaintStateBase {

	protected Face face;

	@Override
	protected void find(
			PaintScreen screen,
			XY p) {
		clearPicked();
		Cp cp = screen.getCp();
		Face found = cp.findFace(p);
		if (found != null) {
			face = found;
			found.setPicked(true);
			return;
		}
		face = null;
	}

	@Override
	protected void findCtrl(
			PaintScreen screen,
			XY p) {
		find(screen, p);
	}

	protected void clearPicked() {
		if (face != null) {
			face.setPicked(false);
		}
		face = null;
	}

}
