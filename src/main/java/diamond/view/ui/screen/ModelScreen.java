/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.action.screen.ScreenAction;
import diamond.view.draw.ModelDrawer;

/**
 * @author Kei Morisue
 *
 */
public class ModelScreen extends ScreenBase {

	private PaintScreen paintScreen;

	private ModelDrawer drawer = new ModelDrawer();

	public ModelScreen() {
		super();
		setFocusable(true);
		ScreenAction action = new ScreenAction(this);
		addMouseMotionListener(action);
		addMouseListener(action);
		addMouseWheelListener(action);
	}

	@Override
	public void drawComponents(
			Graphics2D g2d) {
		drawer.draw(g2d, paintScreen.getPalette().getCp());
	}

	public void link(
			PaintScreen paintScreen) {
		this.paintScreen = paintScreen;

	}
}
