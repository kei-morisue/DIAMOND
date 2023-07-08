/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Kei Morisue
 *
 */
public class Util {

	public static HashMap<Tuple<Vertex>, Assign.Edge> buildVA(List<Edge> edges) {
		HashMap<Tuple<Vertex>, Assign.Edge> vaMap = new HashMap<Tuple<Vertex>, Assign.Edge>();
		edges.forEach(e -> {
			Assign.Edge assign = e.getA();
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			vaMap.put(new Tuple<Vertex>(v0, v1), assign);
			vaMap.put(new Tuple<Vertex>(v1, v0), assign);
		});

		return vaMap;
	}

	public static HashMap<Tuple<Vertex>, Face> buildVF(List<Face> faces) {
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
