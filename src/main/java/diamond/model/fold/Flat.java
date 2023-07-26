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

/**
 * @author Kei Morisue
 *
 */
public abstract class Flat implements Serializable {
	private double EPS;

	protected ArrayList<Face> faces = new ArrayList<Face>();

	public Flat() {
	}

	public void build(Collection<Vertex> vertices, Collection<Edge> edges) {
		buildVV(vertices, edges);
		buildFV(vertices);
		buildFEnEF(edges);
	}

	private void buildVV(Collection<Vertex> vertices, Collection<Edge> edges) {
		HashMap<Vertex, ArrayList<Vertex>> adjVmap = CpBuilder.getAdj(vertices, edges);
		vertices.forEach(v -> {
			ArrayList<Vertex> adjVs = adjVmap.get(v);
			adjVs.sort(v.new AngleComparator());
			adjVs.forEach(a -> v.getAdj().add(a));
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

	private void buildFV(Collection<Vertex> vertices) {
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

	private void buildFEnEF(Collection<Edge> edges) {
		HashMap<Tuple<Vertex>, Edge> ve = CpBuilder.getVE(edges);
		faces.forEach(f -> {
			ArrayList<Vertex> vs = f.getVertices();
			ArrayList<Edge> faceEdges = f.getEdges();
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

	protected abstract int getMaxFraction();

	public final double getEPS() {
		return EPS;
	}

	public final ArrayList<Face> getFaces() {
		return faces;
	}

}
