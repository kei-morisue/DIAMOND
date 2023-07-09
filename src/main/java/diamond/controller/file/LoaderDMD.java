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
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

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
		connectEdges(cps);

		return palette;
	}

	private void connectEdges(Vector<Cp> cps) {
		for (Cp cp : cps) {
			for (Face face : cp.getFaces()) {
				for (HalfEdge he : face.getHalfEdges()) {
					he.getV0().add(he);
					for (HalfEdge h0 : face.getHalfEdges()) {
						if (h0 == he) {
							continue;
						}
						if (h0.getV0() == he.getV1()) {
							he.connectTo(h0);
						}
					}
				}
				for (HalfEdge he : face.getUnsettledLines()) {
					he.getV0().add(he);
					he.connectTo(he.getPair());
				}
			}
		}
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