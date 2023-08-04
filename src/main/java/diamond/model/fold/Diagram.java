/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.LinkedList;

/**
 * @author Kei Morisue
 *
 */
public class Diagram {

	private LinkedList<Cp> cps = new LinkedList<Cp>();
	private int currentCp;

	public Diagram(double size) {
		Cp cp = new Cp(size);
		cps.add(cp);
	}

	public void insert() {
		Cp cp = getCp();
		cps.add(currentCp + 1, new Cp(cp));
		++currentCp;
	}

	public void remove() {
		Cp cp = getCp();
		cps.remove(cp);
		currentCp = currentCp >= cps.size() - 1 ? cps.size() - 1 : currentCp;
	}

	public int getStepNo() {
		return currentCp + 1;

	}

	public int size() {
		return cps.size();
	}

	public Cp getCp() {
		return cps.get(currentCp);
	}

	public void next() {
		currentCp = currentCp == cps.size() - 1 ? currentCp : currentCp + 1;
	}

	public void last() {
		currentCp = cps.size() - 1;
	}

	public void prev() {
		currentCp = currentCp == 0 ? currentCp : currentCp - 1;
	}

	public void first() {
		currentCp = 0;
	}

}
