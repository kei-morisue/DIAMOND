/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import com.sun.tools.javac.util.Pair;

/**
 * @author Kei Morisue
 *
 */
public class Tuple<T> extends Pair<T, T> {

	public Tuple(T fst, T snd) {
		super(fst, snd);
	}

}
