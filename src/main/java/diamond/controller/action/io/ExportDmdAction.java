/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action.io;

import java.awt.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import diamond.model.fold.Diagram;

/**
 * @author Kei Morisue
 *
 */
public class ExportDmdAction extends ExportActionBase {

	private static final String FORMAT = "dmd";
	private static final String FORMAT_DESCRIPTION = "DIAMOND file(*.dmd)";

	public ExportDmdAction(Diagram diagram, Component parent) {
		super(diagram, parent);
	}

	@Override
	protected void export(
			String filePath)
			throws IOException {
		String ext = filePath.substring(1 + filePath.lastIndexOf("."))
				.equals(FORMAT) ? "" : "." + FORMAT;
		try {
			exportXml(diagram, filePath + ext);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getClass().getName());
		}
	}

	private void exportXml(
			Object o,
			String path)
			throws IOException {
		ObjectOutputStream enc
				= new ObjectOutputStream(new FileOutputStream(path));
		enc.writeObject(o);
		enc.close();
	}

	@Override
	protected int getSelectionMode() {
		return JFileChooser.FILES_ONLY;
	}

	@Override
	protected void setFileFilter(
			JFileChooser chooser) {
		FileNameExtensionFilter filter
				= new FileNameExtensionFilter(FORMAT_DESCRIPTION, FORMAT);
		chooser.setFileFilter(filter);
	}

}
