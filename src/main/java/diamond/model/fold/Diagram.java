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
	private int currentIdx;

	public Diagram(double size) {
		Cp cp = new Cp(size);
		cps.add(cp);
	}

	public void insert() {
		Cp cp = getCp();
		cps.add(currentIdx + 1, new Cp(cp));
		++currentIdx;
	}

	public void remove() {
		Cp cp = getCp();
		cps.remove(cp);
		currentIdx = currentIdx >= cps.size() - 1 ? cps.size() - 1 : currentIdx;
	}

	public int getStepNo() {
		return currentIdx + 1;

	}

	public int size() {
		return cps.size();
	}

	public Cp getCp() {
		return cps.get(currentIdx);
	}

	public Cp getCp(
			int j) {
		return cps.get(j);
	}

	public void next() {
		currentIdx = currentIdx == cps.size() - 1 ? currentIdx : currentIdx + 1;
	}

	public void last() {
		currentIdx = cps.size() - 1;
	}

	public void prev() {
		currentIdx = currentIdx == 0 ? currentIdx : currentIdx - 1;
	}

	public void first() {
		currentIdx = 0;
	}

}
