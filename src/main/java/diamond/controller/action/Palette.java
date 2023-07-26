/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.controller.action;

import java.util.LinkedList;

import diamond.model.fold.Cp;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class Palette {

	private LinkedList<Cp> cps = new LinkedList<Cp>();
	private int currentCp;
	public Vertex pointedVertex;

	public Palette(double size) {
		Cp cp = new Cp(size);
		cps.add(cp);
	}

	public Cp getCp() {
		return cps.get(currentCp);
	}

}
