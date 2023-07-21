/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import diamond.model.Tuple;
import diamond.model.XY;
import diamond.model.fold.Edge.Assign;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {

	public static void buildSquare(Cp cp, double scale) {
		ArrayList<Vertex> vertices = cp.getVertices();
		Vertex v0 = new Vertex(new XY(scale, scale));
		Vertex v1 = new Vertex(new XY(scale, -scale));
		Vertex v2 = new Vertex(new XY(-scale, -scale));
		Vertex v3 = new Vertex(new XY(-scale, scale));
		vertices.addAll(Arrays.asList(v0, v1, v2, v3));

		Edge e0 = new Edge(v0, v1, Assign.BOUND);
		Edge e1 = new Edge(v1, v2, Assign.BOUND);
		Edge e2 = new Edge(v2, v3, Assign.BOUND);
		Edge e3 = new Edge(v3, v0, Assign.BOUND);
		cp.getEdges().addAll(Arrays.asList(e0, e1, e2, e3));
		cp.build();
	}

	public static HashMap<Vertex, ArrayList<Vertex>> getAdj(List<Vertex> vertices, List<Edge> edges) {
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

	public static HashMap<Tuple<Vertex>, Edge> getVE(List<Edge> edges) {
		HashMap<Tuple<Vertex>, Edge> vaMap = new HashMap<Tuple<Vertex>, Edge>();
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			vaMap.put(new Tuple<Vertex>(v0, v1), e);
			vaMap.put(new Tuple<Vertex>(v1, v0), e);
		});

		return vaMap;
	}

	public static HashMap<Tuple<Vertex>, Face> getVF(List<Face> faces) {
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
