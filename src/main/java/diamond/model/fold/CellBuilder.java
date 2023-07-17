/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import diamond.model.Tuple;

/**
 * @author Kei Morisue
 *
 */
public class CellBuilder {

	public static HashMap<Tuple<Vertex>, ArrayList<Face>> getSFMap(List<ArrayList<Edge>> se, List<Edge> segments) {
		HashMap<Tuple<Vertex>, ArrayList<Face>> sFmap = new HashMap<Tuple<Vertex>, ArrayList<Face>>();
		for (int i = 0; i < segments.size(); i++) {
			Edge s = segments.get(i);
			ArrayList<Face> fs = new ArrayList<Face>();
			ArrayList<Edge> edges = se.get(i);
			edges.forEach(e -> {
				Face f0 = e.getF0();
				Face f1 = e.getF1();
				fs.add(f0);
				if (f1 != null) {
					fs.add(f1);
				}
			});
			Vertex v0 = s.getV0();
			Vertex v1 = s.getV1();
			sFmap.put(new Tuple<Vertex>(v0, v1), fs);
			sFmap.put(new Tuple<Vertex>(v1, v0), fs);
		}
		return sFmap;
	}

	public static HashMap<Tuple<Vertex>, Face> getSCmap(List<Face> faces) {
		return FoldBuilder.getVF(faces);
	}

	public static ArrayList<Tuple<Face>> getBF(List<ArrayList<Face>> cF) {
		HashSet<Tuple<Face>> bFset = new HashSet<Tuple<Face>>();
		cF.forEach(faces -> {
			for (int j = 0; j < faces.size(); j++) {
				Face f1 = faces.get(j);
				for (int k = j + 1; k < faces.size(); k++) {
					Face f2 = faces.get(k);
					bFset.add(new Tuple<Face>(f1, f2));
				}
			}
		});
		return new ArrayList<Tuple<Face>>(bFset);
	}

}
