/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;

/**
 * @author Kei Morisue
 *
 */
public class LoaderDMD implements Loader {

	@Override
	public Palette load(String filepath) throws IOException, ClassNotFoundException {
		Palette palette = new Palette();
		Vector<Cp> cps = palette.getCps();
		add(cps, filepath);
		cps.remove(0);

		return palette;
	}

	@SuppressWarnings("unchecked")
	private void add(Vector<Cp> cps, String path) throws IOException, ClassNotFoundException {
		System.out.println("Loading: " + path);
		ObjectInputStream decoder = new ObjectInputStream(new FileInputStream(path));
		Object object = decoder.readObject();
		Vector<Cp> cps2 = (Vector<Cp>) object;
		cps2.forEach(cp -> cps.add(cp));
		decoder.close();

	}

}