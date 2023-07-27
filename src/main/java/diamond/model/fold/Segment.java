/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.tools.javac.util.Pair;

import diamond.model.Dir;
import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public abstract class Segment extends Renderable implements Serializable {
	public static final int NONE = 0;
	public static final int MOUNTAIN = 1;
	public static final int VALLEY = -1;
	protected Vertex v0;
	protected Vertex v1;
	protected int a;

	public Segment(Vertex v0, Vertex v1, int a) {
		super();
		this.v0 = v0;
		this.v1 = v1;
		this.a = a;
	}

	public boolean isValley() {
		return a == VALLEY;
	}

	public boolean add(
			Cp cp) {
		Set<Segment> segs = cp.getSegments();
		segs.add(this);
		List<Line> lines = new ArrayList<Line>();
		Map<Line, Segment> lsMap = new HashMap<Line, Segment>();
		segs.forEach(e -> {
			Line line = new Line(e.v0.p, e.v1.p);
			lines.add(line);
			lsMap.put(line, e);
		});
		double eps = Geo.minLength(lines) / 300;
		ArrayList<ArrayList<Pair<XY, Line>>> compressedP
				= Line.getCompressedP(lines, eps);
		ArrayList<Vertex> vs = Line.getV(compressedP);
		HashMap<Tuple<Vertex>, ArrayList<Line>> el
				= Line.getEL(vs, lines, compressedP);
		HashSet<Edge> edges = new HashSet<Edge>();
		HashSet<Crease> creases = new HashSet<Crease>();
		el.forEach((
				vv,
				ls) -> {
			Line line = ls.get(0);
			Segment seg = lsMap.get(line);
			seg.add(vv.fst, vv.snd, edges, creases);
		});
		cp.fold(edges, creases);
		return true;
	};

	public abstract void add(
			Vertex v0,
			Vertex v1,
			Collection<Edge> edges,
			Collection<Crease> creases);

	public XY foot(
			XY p0,
			Dir dir0) {
		Dir n = dir().perp();
		double den = n.dot(dir0);
		Dir dv = p0.dir(v0.p);
		if (den == 0) {
			return dir0.mul(dv.mg() / dir0.mg()).ver(p0);
		} else {
			return dir0.mul(dv.dot(n) / den).ver(p0);
		}
	}

	public double distanceSq(
			XY p) {
		Dir x0 = v0.p.dir(p);
		Dir d = dir();
		double dot = x0.dot(d);
		if (dot <= 0) {
			return v0.p.distanceSq(p);
		}
		double mgSq = d.mgSq();
		if (dot >= mgSq) {
			return v1.p.distanceSq(p);
		}
		double cross = x0.cross(d);
		return cross * cross / mgSq;

	}

	@Override
	public XY centroid() {
		return v0.p.mid(v1.p);
	}

	public Dir dir() {
		return v0.p.dir(v1.p);
	}

	public Dir dirF() {
		return v0.f.dir(v1.f);
	}

	public Vertex getV0() {
		return v0;
	}

	public Vertex getV1() {
		return v1;
	}

	public int getA() {
		return a;
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

}
