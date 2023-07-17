/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.sun.tools.javac.util.Pair;

import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;
import diamond.model.fold.Edge.Assign;
import diamond.model.line.Line;

/**
 * @author Kei Morisue
 *
 */
public abstract class Flat implements Serializable {
	private double EPS;

	protected ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	protected ArrayList<Edge> edges = new ArrayList<Edge>();
	protected ArrayList<Face> faces = new ArrayList<Face>();

	public Flat() {
	}

	public Flat(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		build(l, a);
	}

	protected HashMap<Tuple<Vertex>, ArrayList<Line>> build(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		this.EPS = Geo.minLength(l) / getMaxFraction();
		ArrayList<ArrayList<Pair<XY, Line>>> compressedP = Line.getCompressedP(l, EPS);
		this.vertices = Line.getV(compressedP);
		HashMap<Tuple<Vertex>, ArrayList<Line>> el = Line.getEL(this.vertices, l, compressedP);
		el.forEach((k, v) -> {
			Vertex v0 = k.fst;
			Vertex v1 = k.snd;
			Line line = v.get(0);
			Assign assign = a.get(l.indexOf(line));
			edges.add(new Edge(v0, v1, assign));
		});
		buildVV();
		buildFV();
		buildFEnEF();
		edges.forEach(e -> {
			if (e.getF1() == null) {
				e.setA(Edge.Assign.B);
			}
		});
		return el;
	}

	private void buildVV() {
		HashMap<Vertex, ArrayList<Vertex>> adjVmap = FoldBuilder.getAdj(vertices, edges);
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

	private void buildFV() {
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

	private void buildFEnEF() {
		HashMap<Tuple<Vertex>, Edge> ve = FoldBuilder.getVE(edges);
		faces.forEach(f -> {
			ArrayList<Vertex> vs = f.getVertices();
			for (int i = 0; i < vs.size(); i++) {
				Vertex v1 = vs.get(i);
				Vertex v2 = vs.get((i + 1) % vs.size());
				Edge edge = ve.get(new Tuple<Vertex>(v1, v2));
				f.getEdges().add(edge);// Face to Edge
				if (edge.getF0() == null) {
					edge.setF0(f);// Edge to Face #0
				} else {
					edge.setF1(f);// Edge to Face #1
				}
			}
		});

	}

	protected abstract int getMaxFraction();

	public final double getEPS() {
		return EPS;
	}

	public final ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public final ArrayList<Edge> getEdges() {
		return edges;
	}

	public final ArrayList<Face> getFaces() {
		return faces;
	}

}
