/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;

import diamond.controller.action.Palette;
import diamond.controller.action.paint.PaintAction;
import diamond.controller.action.paint.state.LazyState;
import diamond.controller.action.screen.PaintScreenAction;
import diamond.view.draw.CpDrawer;
import diamond.view.draw.DrawerBase;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreen extends ScreenBase {
	private Palette palette;
	private ModelScreen modelScreen;
	private DrawerBase drawer = new CpDrawer();

	private PaintAction paintAction = new PaintAction(new LazyState());

	public PaintScreen(Palette palette, ModelScreen modelScreen) {
		this.palette = palette;
		this.modelScreen = modelScreen;
		modelScreen.link(this);
		PaintScreenAction paintScreenAction = new PaintScreenAction(this);
		addMouseMotionListener(paintScreenAction);
		addMouseListener(paintScreenAction);
	}

	@Override
	public void drawComponents(Graphics2D g2d) {
		drawer.draw(g2d, palette.getCp());
		paintAction.onDraw(g2d, this);
		modelScreen.repaint();
	}

	public PaintAction getPaintAction() {
		return paintAction;
	}

	public Palette getPalette() {
		return palette;
	}
}
