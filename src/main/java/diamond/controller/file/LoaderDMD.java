/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Vector;

import diamond.controller.Palette;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

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
		cps.forEach(cp -> {
			HashSet<HalfEdge> halfEdges = cp.getHalfEdges();
			HashSet<Vertex> vertices = cp.getVertices();
			halfEdges.forEach(he -> {
				Vertex v0 = he.getV0();
				v0.add(he);
			});
		});
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