/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.action.Palette;
import diamond.view.ui.screen.ModelScreen;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Icon;

/**
 * @author Kei Morisue
 *
 */
public class Paint extends JPanel {
	private Palette palette;
	private PaintScreen screen2;

	public Paint(Palette palette, ModelScreen modelScreen) {
		this.palette = palette;
		this.screen2 = new PaintScreen(palette, modelScreen);
		setLayout(new BorderLayout());
		add(screen2, BorderLayout.CENTER);

		JPanel control = new JPanel();
		add(control, BorderLayout.WEST);

		JRadioButton button = new JRadioButton();
		button.setFocusable(false);
		Icon.set(button, "add_v.gif");
		control.add(button);
	}

}
