/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import diamond.controller.Context;
import diamond.controller.Palette;
import diamond.controller.file.ExporterDMD;

/**
 * @author Kei Morisue
 *
 */
public class ExportDmdAction implements ActionListener {
	Context context;
	Component parentComponent;

	public ExportDmdAction(Context context, Component parent) {
		this.context = context;
		this.parentComponent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("DIAMOND file(*.dmd)", "dmd");
		chooser.setFileFilter(filter);
		String path = null;
		if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(parentComponent)) {
			path = chooser.getSelectedFile().getPath();
		}
		if (path != null) {
			ExporterDMD exporterXML = new ExporterDMD();
			Palette palette = context.getPalette();
			exporterXML.export(palette, path);

		}
	}

}