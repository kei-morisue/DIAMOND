/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import diamond.controller.Context;
import diamond.view.resource.IconBuilder;

/**
 * @author Kei Morisue
 *
 */
public class CpInsert extends JButton {
	public CpInsert(Context context) {
		setBackground(Color.white);
		IconBuilder.set(this, "insert.png");
		setFocusable(false);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
					context.insertUnfoldedCp();
					return;
				}
				if ((e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK) {
					context.insertFoldedCp();
					return;
				}
				context.insertCp();
			}
		});
	}
}