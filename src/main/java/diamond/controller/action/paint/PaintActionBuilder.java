/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.paint;

import diamond.controller.action.paint.state.LazyState;
import diamond.controller.action.paint.state.angle.State0;

/**
 * @author Kei Morisue
 *
 */
public class PaintActionBuilder {

	public static PaintAction lazyAction() {
		return new PaintAction(new LazyState());
	}

	public static PaintAction angleAction() {
		return new PaintAction(new State0());
	}
}
