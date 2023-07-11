/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
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
	public static final int MAX_FRACTION = 300;
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private ArrayList<Face> faces = new ArrayList<Face>();

	private double EPS;

	public Fold(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		build(l, a);
	}

	private void build(ArrayList<Line> l, ArrayList<Edge.Assign> a) {
		this.EPS = Geo.minLength(l) / MAX_FRACTION;
		ArrayList<ArrayList<Pair<XY, Line>>> compressedP = Line.getCompressedP(l, EPS);
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
		buildFEnEF();
		edges.forEach(e -> {
			if (e.getF1() == null) {
				e.setA(Edge.Assign.B);
			}
		});
		buildFolded(faces.get(1));
		return;
	}

	public Fold(String path) {
		ArrayList<Line> lines = new ArrayList<Line>();
		ArrayList<Assign> assigns = new ArrayList<Assign>();
		Assign[] as = { Assign.B, Assign.M, Assign.V, Assign.F };
		try {
			String str;
			BufferedReader bfReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));

			while ((str = bfReader.readLine()) != null) {
				String[] split = str.split(" ");
				if (split.length < 5) {
					continue;
				}
				Assign a = as[Integer.parseInt(split[0]) - 1];
				double x1 = Double.parseDouble(split[1]);
				double y1 = Double.parseDouble(split[2]);
				double x2 = Double.parseDouble(split[3]);
				double y2 = Double.parseDouble(split[4]);
				lines.add(new Line(new XY(x1, y1), new XY(x2, y2)));
				assigns.add(a);
			}
			bfReader.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		build(lines, assigns);
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
		for (int i = 0; i < vertices.size(); i++) {
			Vertex vertex = vertices.get(i);
			XY f = vertex.getF();
			if (f == null) {
				vertex.selected = true;
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
