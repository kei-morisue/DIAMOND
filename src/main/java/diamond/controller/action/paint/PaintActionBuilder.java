/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint;

import diamond.controller.action.paint.state.angle.State0;

/**
 * @author Kei Morisue
 *
 */
public class PaintActionBuilder {

	public static PaintAction angleAction() {
		return new PaintAction(new State0());
	}

	public static PaintAction distortionAction() {
		return new PaintAction(
				new diamond.controller.action.paint.state.distort.State0());
	}

	public static PaintAction flipAction() {
		return new PaintAction(
				new diamond.controller.action.paint.state.flip.State0());
	}

	public static PaintAction baseFaceAction() {
		return new PaintAction(
				new diamond.controller.action.paint.state.base.State0());
	}
}
