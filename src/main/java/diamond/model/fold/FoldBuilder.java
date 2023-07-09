/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class FoldBuilder {

	public static Fold build(String path) throws IOException {
		String line;
		ArrayList<XY> p = new ArrayList<XY>();
		ArrayList<XY> q = new ArrayList<XY>();
		ArrayList<Edge.Assign> as = new ArrayList<Edge.Assign>();
		BufferedReader bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		final Edge.Assign[] assigns = { null, Edge.Assign.U, Edge.Assign.M, Edge.Assign.V };
		while ((line = bfReader.readLine()) != null) {
			if (line == "") {
				continue;
			}
			String[] contents = line.split(" ");
			Edge.Assign a = assigns[Integer.parseInt(contents[0])];
			double x0 = Double.parseDouble(contents[1]);
			double y0 = Double.parseDouble(contents[2]);
			double x1 = Double.parseDouble(contents[3]);
			double y1 = Double.parseDouble(contents[4]);
			p.add(new XY(x0, y0));
			q.add(new XY(x1, y1));
			as.add(a);
		}
		bfReader.close();
		return new Fold(p, q, as);
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
