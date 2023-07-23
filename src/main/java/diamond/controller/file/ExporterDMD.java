/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JOptionPane;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public class ExporterDMD {

	public boolean export(Palette palette, String filePath) {
		Vector<Cp> cps = palette.getCps();
		String ext = filePath.substring(1 + filePath.lastIndexOf(".")).equals("dmd") ? "" : ".dmd";
		try {
			exportXml(cps, filePath + ext);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getClass().getName());
			return false;
		}
		return true;
	}

	private void exportXml(Object o, String name) throws IOException {
		ObjectOutputStream enc = new ObjectOutputStream(new FileOutputStream(name));
		enc.writeObject(o);
		enc.close();
	}
}