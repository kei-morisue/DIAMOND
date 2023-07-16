/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.sun.tools.javac.util.Pair;

import diamond.model.Dir;
import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;
import diamond.model.fold.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class Line {
	public XY p;
	public XY q;

	public Line(XY p, XY q) {
		this.p = p;
		this.q = q;
	}

	public static ArrayList<ArrayList<Pair<XY, Line>>> getCompressedP(ArrayList<Line> lines, double eps) {
		ArrayList<Pair<XY, Line>> p0 = new ArrayList<Pair<XY, Line>>();
		ArrayList<Line> crossings = new ArrayList<>();
		for (Line li : lines) {
			XY a = li.p;
			XY b = li.q;
			p0.add(new Pair<XY, Line>(a, li));
			p0.add(new Pair<XY, Line>(b, li));
			for (Line lj : crossings) {
				XY c = lj.p;
				XY d = lj.q;
				XY x = Geo.intersect(a, b, c, d, eps);
				if (x != null) {
					p0.add(new Pair<XY, Line>(x, li));
					p0.add(new Pair<XY, Line>(x, lj));
				}
				if (Geo.on(a, b, c, eps)) {
					p0.add(new Pair<XY, Line>(c, li));
				}
				if (Geo.on(a, b, d, eps)) {
					p0.add(new Pair<XY, Line>(d, li));
				}
				if (Geo.on(c, d, a, eps)) {
					p0.add(new Pair<XY, Line>(a, lj));
				}
				if (Geo.on(c, d, b, eps)) {
					p0.add(new Pair<XY, Line>(b, lj));
				}
			}
			crossings.add(li);
		}
		p0.sort(new Comparator(eps));
		ArrayList<Pair<XY, Line>> curr = new ArrayList<Pair<XY, Line>>();
		curr.add(p0.get(0));
		ArrayList<ArrayList<Pair<XY, Line>>> compressedP = new ArrayList<>();
		compressedP.add(curr);
		for (Pair<XY, Line> point : p0) {
			XY a = curr.get(0).fst;
			XY b = point.fst;
			if (Geo.isClose(a, b, eps)) {
				curr.add(point);
			} else {
				curr = new ArrayList<Pair<XY, Line>>();
				curr.add(point);
				compressedP.add(curr);
			}
		}
		return compressedP;
	}

	public static ArrayList<Vertex> getV(ArrayList<ArrayList<Pair<XY, Line>>> compressedP) {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		compressedP.forEach(ps -> vertices.add(new Vertex(ps.get(0).fst)));
		return vertices;

	}

	public static HashMap<Tuple<Vertex>, ArrayList<Line>> getEdges(List<Vertex> vs, List<Line> lines,
			ArrayList<ArrayList<Pair<XY, Line>>> compressedP) {
		ArrayList<HashSet<Vertex>> lP = new ArrayList<>();
		lines.forEach(l -> lP.add(new HashSet<>()));
		for (int i = 0; i < compressedP.size(); i++) {
			ArrayList<Pair<XY, Line>> points = compressedP.get(i);
			Vertex v = vs.get(i);
			for (Pair<XY, Line> point : points) {
				Line line = point.snd;
				lP.get(lines.indexOf(line)).add(v);
			}
		}
		HashMap<Tuple<Vertex>, ArrayList<Line>> edges = new HashMap<>();
		for (int i = 0; i < lP.size(); i++) {
			Line line = lines.get(i);
			HashSet<Vertex> vertexSet = lP.get(i);
			ArrayList<Vertex> pointsOnLine = new ArrayList<Vertex>(vertexSet);
			pointsOnLine.sort(line.new PointsOnLineComparator());
			Vertex prev = pointsOnLine.get(0);
			for (int j = 0; j < pointsOnLine.size(); j++) {
				if (j == 0) {
					continue;
				}
				Vertex curr = pointsOnLine.get(j);
				Tuple<Vertex> k = makeKey(curr, prev, vs);
				ArrayList<Line> e = edges.get(k);
				if (e == null) {
					edges.put(k, new ArrayList<Line>(Arrays.asList(line)));
				} else {
					e.add(line);
				}
				prev = curr;
			}
		}
		return edges;
	}

	private static class Comparator implements java.util.Comparator<Pair<XY, Line>> {
		private double eps;

		public Comparator(double eps) {
			super();
			this.eps = eps;
		}

		@Override
		public int compare(Pair<XY, Line> l1, Pair<XY, Line> l2) {
			XY v1 = l1.fst;
			XY v2 = l2.fst;
			return v1.new Comparator(eps).compare(v1, v2);
		}
	}

	private static Tuple<Vertex> makeKey(Vertex v1, Vertex v2, List<Vertex> vs) {
		int i1 = vs.indexOf(v1);
		int i2 = vs.indexOf(v2);
		return i1 < i2 ? new Tuple<Vertex>(v1, v2) : new Tuple<Vertex>(v2, v1);
	}

	private class PointsOnLineComparator implements java.util.Comparator<Vertex> {

		Dir dir;

		public PointsOnLineComparator() {
			super();
			dir = p.dir(q);
		}

		@Override
		public int compare(Vertex pk, Vertex qk) {
			return dir.dot(qk.getV().dir(pk.getV())) > 0 ? 1 : -1;
		}
	}
}
