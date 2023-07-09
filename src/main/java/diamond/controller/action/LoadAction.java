/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;

import diamond.controller.Context;
import diamond.controller.Palette;
import diamond.controller.file.LoaderDMD;

/**
 * @author Kei Morisue
 *
 */
public class LoadAction implements ActionListener {
	Context context;
	Component parentComponent;

	public LoadAction(Context context, Component parent) {
		this.context = context;
		this.parentComponent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(parentComponent)) {
			LoaderDMD loader = new LoaderDMD();
			String path = chooser.getSelectedFile().getPath();
			Palette palette = null;
			try {
				palette = loader.load(path);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			context.setPalette(palette);
		}
	}
}