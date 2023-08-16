/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.io;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;

import diamond.model.fold.Diagram;

/**
 * @author Kei Morisue
 *
 */
public abstract class ExportActionBase implements ActionListener {
	Diagram diagram;
	Component parent;

	public ExportActionBase(Diagram diagram, Component parent) {
		this.diagram = diagram;
		this.parent = parent;
	}

	abstract protected void export(
			String path)
			throws IOException;

	public void actionPerformed(
			ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(getSelectionMode());
		setFileFilter(chooser);
		if (chooser.showSaveDialog(
				parent) == JFileChooser.APPROVE_OPTION) {
			try {
				String filePath = chooser.getSelectedFile().getPath();
				export(filePath);
			} catch (IOException exception) {
				System.err.println(exception);
			}
		}
	}

	protected abstract void setFileFilter(
			JFileChooser chooser);

	protected abstract int getSelectionMode();

}