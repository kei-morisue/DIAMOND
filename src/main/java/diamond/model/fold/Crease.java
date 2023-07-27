/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.sun.tools.javac.util.Pair;

import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class Crease extends Segment implements Serializable {

	private Face face;

	public Crease(Vertex v0, Vertex v1, int a) {
		super(v0, v1, a);
	}

	public Crease(Crease crease) {
		super(crease.v0, crease.v1, crease.a);
	}

	@Override
	public boolean add(
			Cp cp) {
		// TODO be simplified
		HashSet<Edge> edges = cp.getEdges();
		HashSet<Crease> creases = cp.getCreases();
		creases.add(this);
		ArrayList<Line> lines = new ArrayList<Line>();
		HashMap<Line, Edge> leMap = new HashMap<Line, Edge>();
		HashMap<Line, Crease> lcMap = new HashMap<Line, Crease>();
		edges.forEach(e -> {
			Line line = new Line(e.v0.p, e.v1.p);
			lines.add(line);
			leMap.put(line, e);
		});
		creases.forEach(e -> {
			Line line = new Line(e.v0.p, e.v1.p);
			lines.add(line);
			lcMap.put(line, e);
		});
		double eps = Geo.minLength(lines) / 300;
		ArrayList<ArrayList<Pair<XY, Line>>> compressedP
				= Line.getCompressedP(lines, eps);
		ArrayList<Vertex> vs = Line.getV(compressedP);
		HashMap<Tuple<Vertex>, ArrayList<Line>> el
				= Line.getEL(vs, lines, compressedP);
		edges.clear();
		creases.clear();
		el.forEach((
				vv,
				ls) -> {
			Line line = ls.get(0);
			Edge edge = leMap.get(line);
			if (edge != null) {
				edges.add(new Edge(vv.fst, vv.snd, edge.getA()));
			} else {
				Crease crease = lcMap.get(line);
				creases.add(new Crease(vv.fst, vv.snd, crease.getA()));
			}
		});
		cp.fold(edges, creases);
		return true;
	}

	public void setFace(
			Face face) {
		this.face = face;
	}

	public Face getFace() {
		return face;
	}

}
