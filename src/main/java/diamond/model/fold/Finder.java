/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.Collection;

import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Finder {

	public static Vertex find(
			Collection<Vertex> vertices,
			XY p,
			double epsSq) {
		for (Vertex v : vertices) {
			if (v.distanceSq(p) < epsSq) {
				return v;
			}
		}
		return null;
	}
}
