/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.action.Palette;
import diamond.controller.action.paint.PaintAction;
import diamond.controller.action.paint.PaintActionBuilder;
import diamond.view.button.PaintButton;
import diamond.view.ui.screen.ModelScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class PaintPanel extends JPanel {
	private PaintScreen paintScreen;
	private JPanel control = new JPanel();
	private ButtonGroup buttonGroup;

	public PaintPanel(Palette palette, ModelScreen modelScreen,
			ButtonGroup buttonGroup) {
		this.paintScreen = new PaintScreen(palette, modelScreen);
		this.buttonGroup = buttonGroup;
		setLayout(new BorderLayout());
		add(paintScreen, BorderLayout.CENTER);

		control.setLayout(new GridLayout(3, 3));
		add(control, BorderLayout.WEST);

		addpaintButton("axiom1.gif", PaintActionBuilder.angleAction());
		addpaintButton("flip.gif", PaintActionBuilder.flipAction());
		addpaintButton("offset.gif", PaintActionBuilder.distortionAction());
	}

	private void addpaintButton(
			String iconName,
			PaintAction paintAction) {
		JRadioButton button = new PaintButton(iconName, paintScreen,
				paintAction);
		control.add(button);
		buttonGroup.add(button);
	}

}
