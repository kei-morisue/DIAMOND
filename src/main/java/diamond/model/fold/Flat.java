/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public abstract class Flat implements Serializable {

	protected ArrayList<Face> faces = new ArrayList<Face>();

	public Flat() {
	}

	public Flat(Collection<Edge> edges,
			Collection<Crease> creases) {
		build(edges, creases);
	}

	protected void build(
			Collection<Edge> edges,
			Collection<Crease> creases) {
		HashSet<Vertex> vertices = buildVertices(edges);
		buildVV(vertices, edges);
		buildFaces(vertices);
		buildFEnEF(edges);
		buildFC(creases);
	}

	private HashSet<Vertex> buildVertices(
			Collection<Edge> edges) {
		HashSet<Vertex> vertices = new HashSet<Vertex>();
		edges.forEach(edge -> {
			Vertex v0 = edge.getV0();
			Vertex v1 = edge.getV1();
			vertices.add(v0);
			vertices.add(v1);
		});
		return vertices;
	}

	private void buildFC(
			Collection<Crease> creases) {
		creases.forEach(crease -> {
			XY c = crease.centroid();
			for (Face face : faces) {
				if (face.isInside(c)) {
					face.getCreases().add(crease);
					crease.setFace(face);
					return;
				}
			}
		});

	}

	private void buildVV(
			Collection<Vertex> vertices,
			Collection<Edge> edges) {
		HashMap<Vertex, ArrayList<Vertex>> adjVmap = getAdjMap(vertices, edges);
		vertices.forEach(v -> {
			v.getAdj().clear();
			ArrayList<Vertex> adjVs = adjVmap.get(v);
			adjVs.sort(v.new AngleComparator());
			adjVs.forEach(a -> v.getAdj().add(a));
		});
	}

	private HashMap<Vertex, ArrayList<Vertex>> getAdjMap(
			Collection<Vertex> vertices,
			Collection<Edge> edges) {
		HashMap<Vertex, ArrayList<Vertex>> adjVmap
				= new HashMap<Vertex, ArrayList<Vertex>>();
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

	private <E> E getPrev(
			List<E> array,
			E e0) {
		for (int i = 0; i < array.size(); i++) {
			E e1 = array.get(i);
			if (e0 == e1) {
				int index1 = i == 0 ? array.size() : i;
				return array.get(index1 - 1);
			}
		}
		return null;
	}

	private void buildFaces(
			Collection<Vertex> vertices) {
		faces.clear();
		HashSet<Tuple<Vertex>> seen = new HashSet<Tuple<Vertex>>();
		vertices.forEach(v1 -> {
			ArrayList<Vertex> adj1 = v1.getAdj();
			adj1.forEach(v2 -> {
				Tuple<Vertex> pair0 = new Tuple<Vertex>(v1, v2);
				if (!seen.contains(pair0)) {
					seen.add(pair0);
					ArrayList<Vertex> vs = new ArrayList<Vertex>();
					vs.add(pair0.fst);
					Tuple<Vertex> pair1 = pair0;
					while (pair1.snd != pair0.fst) {
						Vertex snd = pair1.snd;
						vs.add(snd);
						ArrayList<Vertex> adj2 = snd.getAdj();
						Vertex fst = pair1.fst;
						pair1 = new Tuple<Vertex>(snd, getPrev(adj2, fst));
						seen.add(pair1);
					}
					if (vs.size() > 2) {
						faces.add(new Face(vs));
					}
				}
			});
		});
		Collections.sort(faces);
		faces.remove(faces.size() - 1);// remove the largest face ~ outer face
	}

	private void buildFEnEF(
			Collection<Edge> edges) {
		HashMap<Tuple<Vertex>, Edge> ve = getVE(edges);
		faces.forEach(f -> {
			ArrayList<Vertex> vs = f.getVertices();
			ArrayList<Edge> faceEdges = f.getEdges();
			faceEdges.clear();
			for (int i = 0; i < vs.size(); i++) {
				Vertex v1 = vs.get(i);
				Vertex v2 = vs.get((i + 1) % vs.size());
				Edge edge = ve.get(new Tuple<Vertex>(v1, v2));
				faceEdges.add(edge);// Face to Edge
				if (edge.getF0() == null) {
					edge.setF0(f);// Edge to Face #0
				} else {
					edge.setF1(f);// Edge to Face #1
				}
			}
		});
		edges.forEach(edge -> {
			if (edge.getF1() == null) {
				edge.setF1(edge.getF0());
			}
		});// boundary has f0 as f1

	}

	private HashMap<Tuple<Vertex>, Edge> getVE(
			Collection<Edge> edges) {
		HashMap<Tuple<Vertex>, Edge> vaMap = new HashMap<Tuple<Vertex>, Edge>();
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			vaMap.put(new Tuple<Vertex>(v0, v1), e);
			vaMap.put(new Tuple<Vertex>(v1, v0), e);
		});

		return vaMap;
	}

	public final ArrayList<Face> getFaces() {
		return faces;
	}

}
