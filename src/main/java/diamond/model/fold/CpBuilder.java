/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {

	// TODO stub
	public static void buildSquare(Cp cp, double scale) {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		Vertex v0 = new Vertex(new XY(scale, scale));
		Vertex v1 = new Vertex(new XY(scale, -scale));
		Vertex v2 = new Vertex(new XY(-scale, -scale));
		Vertex v3 = new Vertex(new XY(-scale, scale));
		Vertex m = new Vertex(new XY(0, 0));
		Vertex m1 = new Vertex(new XY(0, scale));
		Vertex m2 = new Vertex(new XY(0, -scale));
		vertices.addAll(Arrays.asList(v0, v1, v2, v3, m, m1, m2));

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
		edges.addAll(Arrays.asList(e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11));
		cp.build(vertices, edges);

	}

	public static HashMap<Vertex, ArrayList<Vertex>> getAdj(Collection<Vertex> vertices, Collection<Edge> edges) {
		HashMap<Vertex, ArrayList<Vertex>> adjVmap = new HashMap<Vertex, ArrayList<Vertex>>();
		vertices.forEach(v -> {
			adjVmap.put(v, new ArrayList<Vertex>());
		});
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			adjVmap.get(v0).add(v1);
			adjVmap.get(v1).add(v0);
		});
		return adjVmap;
	}

	public static HashMap<Tuple<Vertex>, Edge> getVE(Collection<Edge> edges) {
		HashMap<Tuple<Vertex>, Edge> vaMap = new HashMap<Tuple<Vertex>, Edge>();
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			vaMap.put(new Tuple<Vertex>(v0, v1), e);
			vaMap.put(new Tuple<Vertex>(v1, v0), e);
		});

		return vaMap;
	}

	public static HashMap<Tuple<Vertex>, Face> getVF(Collection<Face> faces) {
		HashMap<Tuple<Vertex>, Face> vfMap = new HashMap<Tuple<Vertex>, Face>();
		faces.forEach(f -> {
			ArrayList<Vertex> vs = f.getVertices();
			for (int j = 0; j < vs.size(); j++) {
				Vertex v1 = vs.get(j);
				Vertex v2 = vs.get((j + 1) % vs.size());
				vfMap.put(new Tuple<Vertex>(v2, v1), f);

			}
		});
		return vfMap;
	}

}
