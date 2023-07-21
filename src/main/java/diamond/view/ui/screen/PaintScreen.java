/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.action.Palette;
import diamond.controller.action.paint.PaintActionBase;
import diamond.controller.action.screen.PaintScreenAction;

/**
 * @author Kei Morisue
 *
 */
public class PaintScreen extends ScreenBase {
	private Palette palette;
	private ModelScreen modelScreen;
	public Point2D.Double mousePoint = new Point2D.Double();
	private PaintActionBase paintAction;

	public PaintScreen(Palette context) {
		this.palette = context;
		PaintScreenAction paintScreenAction = new PaintScreenAction(this);
		addMouseMotionListener(paintScreenAction);
		addMouseListener(paintScreenAction);
	}

	@Override
	public void drawComponents(Graphics2D g2d) {
		// TODO 自動生成されたメソッド・スタブ
		modelScreen.repaint();
	}

	public PaintActionBase getPaintAction() {
		return paintAction;
	}

	public Palette getPalette() {
		return palette;
	}
}
