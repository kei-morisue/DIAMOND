/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.io;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import diamond.model.fold.Diagram;

/**
 * @author Kei Morisue
 *
 */
public class ExportAction implements ActionListener {
	Diagram context;
	Component parentComponent;

	public ExportAction(Diagram diagram, Component parent) {
		this.context = diagram;
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