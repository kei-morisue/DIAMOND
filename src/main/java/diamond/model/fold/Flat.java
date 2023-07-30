/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

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
		buildVE(edges);
		buildFEnEF(edges);
		buildFCnVC(vertices, creases);
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

	private void buildFCnVC(
			Collection<Vertex> vertices,
			Collection<Crease> creases) {

		setAdj(vertices, creases);
		creases.forEach(crease -> {
			XY c = crease.centroid();
			Vertex v0 = crease.getV0();
			Vertex v1 = crease.getV1();
			v0.putCrease(v1, crease);
			v1.putCrease(v0, crease);
			Face face = findFace(crease, c);
			if (face != null) {
				face.add(crease);
			}
		});

	}

	private Face findFace(
			Crease crease,
			XY c) {
		for (Face face : faces) {
			boolean inside = face.isInside(c);
			if (inside) {
				return face;
			}
		}
		return null;
	}

	private void buildVV(
			Collection<Vertex> vertices,
			Collection<Edge> edges) {
		vertices.forEach(v -> {
			v.getAdj().clear();
		});
		setAdj(vertices, edges);
	}

	private <T extends Segment> void setAdj(
			Collection<Vertex> vertices,
			Collection<T> edges) {
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			v0.getAdj().add(v1);
			v1.getAdj().add(v0);
		});
		vertices.forEach(v -> {
			ArrayList<Vertex> adj = v.getAdj();
			adj.sort(v.new AngleComparator());
		});
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
		faces.forEach(f -> {
			f.setEdges();
		});
		edges.forEach(edge -> {
			if (edge.getF1() == null) {
				edge.setF1(edge.getF0());
			}
		});// boundary has f0 as f1

	}

	private void buildVE(
			Collection<Edge> edges) {
		edges.forEach(e -> {
			Vertex v0 = e.getV0();
			Vertex v1 = e.getV1();
			v0.putEdge(v1, e);
			v1.putEdge(v0, e);
		});
	}

	public void forFaces(
			Consumer<Face> action) {
		faces.forEach(action);
	}

	public int size() {
		return faces.size();

	}
}
