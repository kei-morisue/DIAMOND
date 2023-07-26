/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import diamond.controller.action.paint.PaintAction;
import diamond.view.ui.screen.PaintScreen;
import diamond.view.util.Icon;

/**
 * @author Kei Morisue
 *
 */
public class PaintButton extends JRadioButton implements ActionListener {

	private PaintScreen paintScreen;

	private PaintAction paintAction;

	public PaintButton(String iconName, PaintScreen paintScreen, PaintAction paintAction) {
		super();
		this.paintScreen = paintScreen;
		this.paintAction = paintAction;
		setFocusable(false);
		Icon.set(this, iconName);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		paintScreen.setPaintAction(this.paintAction);

	}

}
