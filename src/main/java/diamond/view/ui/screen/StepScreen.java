/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.border.MatteBorder;

import diamond.controller.action.screen.ScreenAction;
import diamond.model.fold.Cp;
import diamond.view.draw.ModelDrawer;
import diamond.view.draw.StringDrawer;
import diamond.view.draw.shape.Konst;
import diamond.view.util.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class StepScreen extends ScreenBase {
	private Cp cp;
	private int stepIdx = -1;

	public StepScreen(Cp cp) {
		super();
		this.cp = cp;
		ScreenAction action = new ScreenAction(this);
		addMouseMotionListener(action);
		addMouseListener(action);
		addMouseWheelListener(action);
		setBorder(new MatteBorder(2, 2, 2, 2, Color.black));
	}

	public StepScreen(Cp cp, int stepIdx) {
		this(cp);
		this.stepIdx = stepIdx;
		setBorder(new MatteBorder(0, 0, 1, 0, Color.black));
	}

	@Override
	protected Color getBG() {
		return stepIdx == -1 ? Color.WHITE : Konst.PREVIEW_BG;
	}

	@Override
	public ScreenTransform getTransform() {
		return cp.getScreenTransform();
	}

	@Override
	public void drawComponents(
			Graphics2D g2d) {
		new ModelDrawer().draw(g2d, cp);
		if (stepIdx != -1) {
			StringDrawer.drawPreviewStepNo(g2d, stepIdx + 1, 0, 0);
		}
	}

}
