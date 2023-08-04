/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.action.screen.ScreenAction;
import diamond.model.fold.Diagram;
import diamond.view.draw.ModelDrawer;
import diamond.view.draw.StringDrawer;

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
		Diagram diagram = paintScreen.getPalette();
		drawer.draw(g2d, diagram.getCp());
		StringDrawer.drawStepNo(g2d, diagram.getStepNo(), 0, 0);
		paintScreen.repaint();
	}

	public void link(
			PaintScreen paintScreen) {
		this.paintScreen = paintScreen;

	}
}
