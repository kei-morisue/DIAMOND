/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public class ExporterDMD {

	public boolean export(Palette palette, String filePath) throws IOException {
		Vector<Cp> cps = palette.getCps();
		exportXml(cps, filePath + ".dmd");
		return true;
	}

	private void exportXml(Object o, String name) throws IOException {
		ObjectOutputStream enc = new ObjectOutputStream(new FileOutputStream(name));
		enc.writeObject(o);
		enc.close();
	}
}