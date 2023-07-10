/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.sun.tools.javac.util.Pair;

import diamond.model.Dir;
import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;
import diamond.model.fold.Edge.Assign;
import diamond.model.line.Line;

/**
 * @author Kei Morisue
 *
 */
public class Fold implements Serializable {
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Face> faces = new ArrayList<Face>();

	// TODO STUB
	public Fold(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		double eps = Geo.minLength(l) / 300;
		ArrayList<ArrayList<Pair<XY, Line>>> compressedP = Line.getCompressedP(l, eps);
		this.vertices = Line.getV(compressedP);
		HashMap<Tuple<Vertex>, ArrayList<Line>> es = Line.getEdges(this.vertices, l, compressedP);
		es.forEach((k, v) -> {
			Vertex v0 = k.fst;
			Vertex v1 = k.snd;
			Line line = v.get(0);
			Assign assign = a.get(l.indexOf(line));
			edges.add(new Edge(v0, v1, assign));
		});
		buildVV();
		buildFV();
		buildFolded(faces.get(1));
		buildFEnEF();
		return;
	}

	public static ArrayList<Line> testLines() {
		double size = 100.0;
		XY v0 = new XY(size, size);
		XY v1 = new XY(-size, size);
		XY v2 = new XY(-size, -size);
		XY v3 = new XY(size, -size);

		Line e0 = new Line(v0, v1);
		Line e1 = new Line(v1, v2);
		Line e2 = new Line(v2, v3);
		Line e3 = new Line(v3, v0);
		Line e4 = new Line(v2, v0);
		Line e5 = new Line(v1, v3);
		return new ArrayList<Line>(Arrays.asList(e0, e1, e2, e3, e4, e5));
	}

	public static ArrayList<Edge.Assign> testAssigns() {
		Assign b = Edge.Assign.B;
		Assign m = Edge.Assign.M;
		// Assign v = Edge.Assign.V;
		Assign f = Edge.Assign.F;
		return new ArrayList<Edge.Assign>(Arrays.asList(b, b, b, b, m, f));
	}

	public Fold() {
		this(testLines(), testAssigns());
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

	public void clearFolded() {
		vertices.forEach(v -> v.setF(null));
	}

	private void buildFolded(Face baseFace) {
		HashMap<Tuple<Vertex>, Edge> ve = FoldBuilder.getVE(edges);
		HashMap<Tuple<Vertex>, Face> vf = FoldBuilder.getVF(faces);
		clearFolded();
		HashSet<Face> queued = new HashSet<Face>();
		Vertex p0 = baseFace.getVertices().get(0);
		Vertex p1 = baseFace.getVertices().get(1);
		p0.setF(p0.getV());
		p1.setF(p1.getV());
		ArrayDeque<Face> queueFace = new ArrayDeque<>(Arrays.asList(baseFace));
		queued.add(baseFace);
		ArrayDeque<Vertex> queueV0 = new ArrayDeque<Vertex>(Arrays.asList(p0));
		ArrayDeque<Vertex> queueV1 = new ArrayDeque<Vertex>(Arrays.asList(p1));
		ArrayDeque<Boolean> queueFlip = new ArrayDeque<Boolean>(Arrays.asList(true));
		while (queueFace.size() > 0) {
			Face face = queueFace.poll();
			Vertex v0 = queueV0.poll();
			Vertex v1 = queueV1.poll();
			Boolean flip = queueFlip.poll();
			face.setFlip(!flip);
			ArrayList<Vertex> vs = face.getVertices();
			Dir x = v0.getV().dir(v1.getV()).unit();
			Dir y = x.perp();
			Dir xf = v0.getF().dir(v1.getF()).unit();
			Dir yf = xf.perp();
			Vertex vi = vs.get(vs.size() - 1);
			for (Vertex vj : vs) {
				if (vj.getF() == null) {
					Dir dir = v0.getV().dir(vj.getV());
					Dir dx = xf.mul(dir.dot(x));
					Dir dy = yf.mul(dir.dot(y) * (flip ? 1 : -1));
					vj.setF(dx.add(dy).ver(v0.getF()));
				}
				// adding next queue
				Tuple<Vertex> key = new Tuple<Vertex>(vi, vj);
				Face nextFace = vf.get(key);
				boolean nextFlip = (ve.get(key).isFlipping()) ? !flip : flip;
				if (nextFace != null && !queued.contains(nextFace)) {
					queueFace.add(nextFace);
					queueV0.add(vj);
					queueV1.add(vi);
					queueFlip.add(nextFlip);
					queued.add(nextFace);
				}
				vi = vj;
			}
		}

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
