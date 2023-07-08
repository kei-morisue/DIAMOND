/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author Kei Morisue
 *
 */
public class Fold implements Serializable {
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Face> faces = new ArrayList<Face>();

	// TODO STUB
	public Fold(ArrayList<XY> p, ArrayList<XY> q, ArrayList<Assign.Edge> a) {
	}

	public Fold() {
		double size = 100.0;
		Vertex v0 = new Vertex(new XY(size, size));
		Vertex v1 = new Vertex(new XY(-size, size));
		Vertex v2 = new Vertex(new XY(-size, -size));
		Vertex v3 = new Vertex(new XY(size, -size));

		// TODO STUB
		v0.setF(new XY(size, size));
		v1.setF(new XY(-size, size));
		v2.setF(new XY(-size, -size));
		v3.setF(new XY(-size, size));

		vertices.add(v0);
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);

		Edge e0 = new Edge(v0, v1, Assign.Edge.B);
		Edge e1 = new Edge(v1, v2, Assign.Edge.B);
		Edge e2 = new Edge(v2, v3, Assign.Edge.B);
		Edge e3 = new Edge(v3, v0, Assign.Edge.B);
		Edge e4 = new Edge(v0, v2, Assign.Edge.M);
		edges.add(e0);
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		buildVV();
		buildFV();
		return;
	}

	private void buildVV() {
		HashMap<Vertex, ArrayList<Vertex>> adjV = new HashMap<Vertex, ArrayList<Vertex>>();
		vertices.forEach(v -> {
			adjV.put(v, new ArrayList<Vertex>());
		});
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			adjV.get(v0).add(v1);
			adjV.get(v1).add(v0);
		});
		vertices.forEach(v -> {
			ArrayList<Vertex> adjVs = adjV.get(v);
			adjVs.sort(v.new AngleComparator());
			adjVs.forEach(a -> v.getVertices().add(a));
		});
	}

	private <E> E getPrev(List<E> array, E e0) {
		for (int i = 0; i < array.size(); i++) {
			E e1 = array.get(i);
			if (e0 == e1) {
				int index1 = i == 0 ? array.size() : i;
				return array.get(index1 - 1);
			}
		}
		return null;
	}

	private void buildFV() {
		HashSet<Tuple<Vertex>> seen = new HashSet<Tuple<Vertex>>();
		vertices.forEach(v1 -> {
			ArrayList<Vertex> vv = v1.getVertices();
			vv.forEach(v2 -> {
				Tuple<Vertex> pair0 = new Tuple<Vertex>(v1, v2);
				if (!seen.contains(pair0)) {
					seen.add(pair0);
					ArrayList<Vertex> face = new ArrayList<Vertex>();
					face.add(pair0.fst);
					Tuple<Vertex> pair1 = pair0;
					while (pair1.snd != pair0.fst) {
						Vertex snd = pair1.snd;
						face.add(snd);
						ArrayList<Vertex> vv2 = snd.getVertices();
						Vertex fst = pair1.fst;
						pair1 = new Tuple<Vertex>(snd, getPrev(vv2, fst));
						seen.add(pair1);
					}
					if (face.size() > 2) {
						faces.add(new Face(face));
					}
				}
			});
		});
		Collections.sort(faces);
		faces.remove(faces.size() - 1);// remove the largest face ~ outer face
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public ArrayList<Face> getFaces() {
		return faces;
	}

}
