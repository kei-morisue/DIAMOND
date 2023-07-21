/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import diamond.controller.action.Palette;
import diamond.view.ui.screen.ScreenPaint;
import diamond.view.util.Icon;

/**
 * @author Kei Morisue
 *
 */
public class Paint extends JPanel {
	private Palette palette;
	private ScreenPaint screen2;

	public Paint(Palette palette) {
		this.palette = palette;
		this.screen2 = new ScreenPaint(palette);
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
