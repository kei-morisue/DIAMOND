/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.action.Palette;
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

	public PaintPanel(Palette palette, ModelScreen modelScreen) {
		this.paintScreen = new PaintScreen(palette, modelScreen);
		setLayout(new BorderLayout());
		add(paintScreen, BorderLayout.CENTER);

		JPanel control = new JPanel();
		control.setLayout(new GridLayout(3, 3));
		add(control, BorderLayout.WEST);

		JRadioButton button1 = new PaintButton("add_v.gif", paintScreen, PaintActionBuilder.angleAction());
		JRadioButton button2 = new PaintButton("axiom1.gif", paintScreen, PaintActionBuilder.lazyAction());
		control.add(button1);
		control.add(button2);
	}

}
