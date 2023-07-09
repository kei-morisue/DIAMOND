/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import diamond.model.fold.Fold;
import diamond.view.draw.Drawer;
import diamond.view.draw.DrawerBase;
import diamond.view.draw.color.ColorProviderFlat;
import diamond.view.draw.shape.ShapeProviderBase;

/**
 * @author Kei Morisue
 *
 */
public class ScreenFold extends ScreenBase {
	private Drawer drawer;

	public ScreenFold(Fold fold, ShapeProviderBase shapeProvider) {
		super();
		this.drawer = new Drawer(fold, new ColorProviderFlat(), shapeProvider);
	}

	@Override
	protected DrawerBase getDrawer() {
		return drawer;
	}
}
