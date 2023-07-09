/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class ScreenFold extends ScreenBase {
	private DrawerBase drawer;

	public ScreenFold(DrawerBase drawer) {
		super();
		this.drawer = drawer;
	}

	@Override
	protected DrawerBase getDrawer() {
		return drawer;
	}
}
