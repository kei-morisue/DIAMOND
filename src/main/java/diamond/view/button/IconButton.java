/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.view.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JButton;

import diamond.view.util.Icon;

/**
 * @author Kei Morisue
 *
 */
public class IconButton extends JButton implements ActionListener {
	Consumer<? super ActionEvent> action;

	public IconButton(String iconName, Consumer<? super ActionEvent> action) {
		super();
		this.action = action;
		setFocusable(false);
		Icon.set(this, iconName);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(
			ActionEvent e) {
		this.action.accept(e);
	}

}
