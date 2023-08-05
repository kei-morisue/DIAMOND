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

import diamond.controller.action.paint.PaintAction;
import diamond.controller.action.paint.PaintActionBuilder;
import diamond.view.button.PaintButton;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class PaintPanel extends JPanel {
	private PaintScreen paintScreen;
	private JPanel control = new JPanel();
	private JPanel control2 = new JPanel();
	private ButtonGroup buttonGroup;

	public PaintPanel(PaintScreen paintScreen,
			ButtonGroup buttonGroup) {
		this.paintScreen = paintScreen;
		this.buttonGroup = buttonGroup;
		setLayout(new BorderLayout());
		add(paintScreen, BorderLayout.CENTER);

		control.setLayout(new GridLayout(6, 1));
		add(control, BorderLayout.WEST);

		addpaintButton("axiom1.gif", PaintActionBuilder.angleAction(), control);
		addpaintButton("flip.gif", PaintActionBuilder.flipAction(), control);
		addpaintButton("offset.gif", PaintActionBuilder.distortionAction(),
				control);
		addpaintButton("base_face.gif", PaintActionBuilder.baseFaceAction(),
				control);

		add(control2, BorderLayout.EAST);
		control2.setLayout(new GridLayout(6, 1));
		addpaintButton("landmark.gif", PaintActionBuilder.symbolAction(),
				control2);
	}

	private void addpaintButton(
			String iconName,
			PaintAction paintAction,
			JPanel panel) {
		JRadioButton button = new PaintButton(iconName, paintScreen,
				paintAction);
		panel.add(button);
		buttonGroup.add(button);
	}

}
