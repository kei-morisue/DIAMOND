/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.sun.tools.javac.util.Pair;

import diamond.model.Dir;
import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Line {
	protected Vertex v0;
	protected Vertex v1;

	public Line(Vertex v0, Vertex v1) {
		this.v0 = v0;
		this.v1 = v1;
	}

	public Vertex getPair(
			Vertex v) {
		if (v == v0) {
			return v1;
		}
		if (v == v1) {
			return v0;
		}
		return null;
	}

	public Dir dir() {
		return v0.dir(v1);
	}

	public Dir dirF() {
		return v0.f.dir(v1.f);
	}

	public double distanceSq(
			XY p) {
		Dir x0 = v0.dir(p);
		Dir d = dir();
		double dot = x0.dot(d);
		if (dot <= 0) {
			return v0.distanceSq(p);
		}
		double mgSq = d.mgSq();
		if (dot >= mgSq) {
			return v1.distanceSq(p);
		}
		double cross = x0.cross(d);
		return cross * cross / mgSq;

	}

	public XY foot(
			XY p0,
			Dir dir0) {
		Dir n = dir().perp();
		double den = n.dot(dir0);
		Dir dv = p0.dir(v0);
		if (den == 0) {
			return dir0.mul(dv.mg() / dir0.mg()).ver(p0);
		} else {
			return dir0.mul(dv.dot(n) / den).ver(p0);
		}
	}

	public static <
			T extends Line> ArrayList<ArrayList<Pair<XY, T>>> getCompressedP(
					Collection<T> lines,
					double eps) {
		ArrayList<Pair<XY, T>> p0 = new ArrayList<Pair<XY, T>>();
		ArrayList<T> crossings = new ArrayList<>();
		for (T li : lines) {
			XY a = li.v0;
			XY b = li.v1;
			p0.add(new Pair<XY, T>(a, li));
			p0.add(new Pair<XY, T>(b, li));
			for (T lj : crossings) {
				XY c = lj.v0;
				XY d = lj.v1;
				XY x = Geo.intersect(a, b, c, d, eps);
				if (x != null) {
					p0.add(new Pair<XY, T>(x, li));
					p0.add(new Pair<XY, T>(x, lj));
				}
				if (Geo.on(a, b, c, eps)) {
					p0.add(new Pair<XY, T>(c, li));
				}
				if (Geo.on(a, b, d, eps)) {
					p0.add(new Pair<XY, T>(d, li));
				}
				if (Geo.on(c, d, a, eps)) {
					p0.add(new Pair<XY, T>(a, lj));
				}
				if (Geo.on(c, d, b, eps)) {
					p0.add(new Pair<XY, T>(b, lj));
				}
			}
			crossings.add(li);
		}
		p0.sort(new Comparator<T>(eps));
		ArrayList<Pair<XY, T>> curr = new ArrayList<Pair<XY, T>>();
		curr.add(p0.get(0));
		ArrayList<ArrayList<Pair<XY, T>>> compressedP = new ArrayList<>();
		compressedP.add(curr);
		for (Pair<XY, T> point : p0) {
			XY a = curr.get(0).fst;
			XY b = point.fst;
			if (Geo.isClose(a, b, eps)) {
				curr.add(point);
			} else {
				curr = new ArrayList<Pair<XY, T>>();
				curr.add(point);
				compressedP.add(curr);
			}
		}
		return compressedP;
	}

	public static <T extends Line> ArrayList<Vertex> getV(
			ArrayList<ArrayList<Pair<XY, T>>> compressedP) {
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		compressedP.forEach(ps -> vertices.add(new Vertex(ps.get(0).fst)));
		return vertices;

	}

	public static <T extends Line> HashMap<Tuple<Vertex>, ArrayList<T>> getEL(
			List<Vertex> vs,
			Collection<T> lines,
			ArrayList<ArrayList<Pair<XY, T>>> compressedP) {
		HashMap<T, HashSet<Vertex>> lP = new HashMap<>();
		lines.forEach(l -> lP.put(l, new HashSet<>()));
		for (int i = 0; i < compressedP.size(); i++) {
			ArrayList<Pair<XY, T>> points = compressedP.get(i);
			Vertex v = vs.get(i);
			for (Pair<XY, T> point : points) {
				T line = point.snd;
				lP.get(line).add(v);
			}
		}
		HashMap<Tuple<Vertex>, ArrayList<T>> edges = new HashMap<>();
		for (T line : lines) {
			HashSet<Vertex> vertexSet = lP.get(line);
			ArrayList<Vertex> pointsOnLine = new ArrayList<Vertex>(vertexSet);
			pointsOnLine.sort(line.new PointsOnLineComparator());
			Vertex prev = pointsOnLine.get(0);
			for (int j = 0; j < pointsOnLine.size(); j++) {
				if (j == 0) {
					continue;
				}
				Vertex curr = pointsOnLine.get(j);
				Tuple<Vertex> k = makeKey(curr, prev, vs);
				ArrayList<T> e = edges.get(k);
				if (e == null) {
					edges.put(k, new ArrayList<T>(Arrays.asList(line)));
				} else {
					e.add(line);
				}
				prev = curr;
			}
		}
		return edges;
	}

	private static class Comparator<T extends Line>
			implements java.util.Comparator<Pair<XY, T>> {
		private double eps;

		public Comparator(double eps) {
			super();
			this.eps = eps;
		}

		@Override
		public int compare(
				Pair<XY, T> l1,
				Pair<XY, T> l2) {
			XY v1 = l1.fst;
			XY v2 = l2.fst;
			return v1.new Comparator(eps).compare(v1, v2);
		}
	}

	private static Tuple<Vertex> makeKey(
			Vertex v1,
			Vertex v2,
			List<Vertex> vs) {
		int i1 = vs.indexOf(v1);
		int i2 = vs.indexOf(v2);
		return i1 < i2 ? new Tuple<Vertex>(v1, v2) : new Tuple<Vertex>(v2, v1);
	}

	public class PointsOnLineComparator
			implements java.util.Comparator<Vertex> {

		Dir dir;

		public PointsOnLineComparator() {
			super();
			dir = v0.dir(v1);
		}

		@Override
		public int compare(
				Vertex pk,
				Vertex qk) {
			return dir.dot(qk.dir(pk)) > 0 ? 1 : -1;
		}
	}

	public Vertex getV0() {
		return v0;
	}

	public Vertex getV1() {
		return v1;
	}
}
