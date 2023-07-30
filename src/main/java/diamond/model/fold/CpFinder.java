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
public class CpFinder {

	public static Vertex findVertex(
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

	public static Segment findSegment(
			Collection<Segment> segs,
			XY p,
			double epsSq) {
		for (Segment seg : segs) {
			if (seg.distanceSq(p) < epsSq) {
				return seg;
			}
		}
		return null;
	}

}
