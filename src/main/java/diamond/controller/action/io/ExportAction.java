/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.io;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import diamond.controller.action.Palette;

/**
 * @author Kei Morisue
 *
 */
public class ExportAction implements ActionListener {
	Palette context;
	Component parentComponent;

	public ExportAction(Palette context, Component parent) {
		this.context = context;
		this.parentComponent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		String path = null;
		if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(parentComponent)) {
			path = chooser.getSelectedFile().getPath();
		}
		// TODO

	}

}