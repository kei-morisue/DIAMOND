/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import diamond.model.Tuple;

/**
 * @author Kei Morisue
 *
 */
public class FoldBuilder {

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
