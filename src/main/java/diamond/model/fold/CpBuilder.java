/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.Arrays;

import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {

	// TODO stub
	public static void buildSquare(
			Cp cp,
			double scale) {
		Vertex v0 = new Vertex(new XY(scale, scale));
		Vertex v1 = new Vertex(new XY(scale, -scale));
		Vertex v2 = new Vertex(new XY(-scale, -scale));
		Vertex v3 = new Vertex(new XY(-scale, scale));
		Vertex m = new Vertex(new XY(0, 0));
		Vertex m1 = new Vertex(new XY(0, scale));
		Vertex m2 = new Vertex(new XY(0, -scale));
		Vertex n1 = new Vertex(new XY(scale, 0));
		Vertex n2 = new Vertex(new XY(-scale, 0));

		Edge e0 = new Edge(v0, v1, Edge.NONE);
		Edge e1 = new Edge(v1, m2, Edge.NONE);
		Edge e2 = new Edge(m2, v2, Edge.NONE);
		Edge e3 = new Edge(v2, v3, Edge.NONE);
		Edge e4 = new Edge(v3, m1, Edge.NONE);
		Edge e5 = new Edge(m1, v0, Edge.NONE);

		Edge e6 = new Edge(m, v0, Edge.MOUNTAIN);
		Edge e7 = new Edge(m, v1, Edge.MOUNTAIN);
		Edge e8 = new Edge(m, v2, Edge.MOUNTAIN);
		Edge e9 = new Edge(m, v3, Edge.MOUNTAIN);
		Edge e10 = new Edge(m, m1, Edge.VALLEY);
		Edge e11 = new Edge(m, m2, Edge.VALLEY);

		ArrayList<Edge> edges = new ArrayList<Edge>();
		edges.addAll(Arrays.asList(e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10,
				e11));

		ArrayList<Crease> creases = new ArrayList<Crease>();
		Crease c0 = new Crease(m, n1, Edge.NONE);
		Crease c1 = new Crease(m, n2, Edge.NONE);
		creases.addAll(Arrays.asList(c0, c1));

		cp.build(edges, creases);

	}

}
