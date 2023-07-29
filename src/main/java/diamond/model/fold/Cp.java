/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
public class Cp extends Flat {
	transient private Set<Vertex> vertices = new HashSet<Vertex>();
	transient private Set<Segment> segments = new HashSet<Segment>();
	private Face baseFace;
	public double eps;

	public Cp(double scale) {
		super();
		CpBuilder.buildSquare(this, scale);
		this.eps = scale / 300;
	}

	public void rebuild() {
		eps = Geo.minLength(segments) / 300;
		ArrayList<ArrayList<Pair<XY, Segment>>> compressedP
				= Line.getCompressedP(segments, eps);
		ArrayList<Vertex> vs = Line.getV(compressedP);
		HashMap<Tuple<Vertex>, ArrayList<Segment>> el
				= Line.getEL(vs, segments, compressedP);
		HashSet<Edge> edges = new HashSet<Edge>();
		HashSet<Crease> creases = new HashSet<Crease>();
		el.forEach((
				vv,
				lines) -> {
			Segment seg = getEdge(lines);
			seg.add(vv.fst, vv.snd, edges, creases);
		});
		fold(edges, creases);
	}

	public boolean trim() {
		for (Vertex vertex : vertices) {
			if (trim(vertex)) {
				return true;
			}
		}
		return false;
	}

	public boolean trim(
			Vertex v) {
		ArrayList<Vertex> adj = v.getAdj();
		if (adj.size() != 2) {
			return false;
		}
		Vertex v0 = adj.get(0);
		Vertex v1 = adj.get(1);
		Tuple<Vertex> key0 = new Tuple<Vertex>(v, v0);
		Tuple<Vertex> key1 = new Tuple<Vertex>(v, v1);
		Edge e0 = veMap.get(key0);
		Edge e1 = veMap.get(key1);
		if (trim(v0, v1, e0, e1)) {
			return true;
		}
		Crease c0 = vcMap.get(key0);
		Crease c1 = vcMap.get(key1);
		if (trim(v0, v1, c0, c1)) {
			return true;
		}
		return false;
	}

	private boolean trim(
			Vertex v0,
			Vertex v1,
			Segment e0,
			Segment e1) {
		if (e0 != null && e1 != null) {
			int a0 = e0.getA();
			if (a0 == e1.getA()) {
				double angle0 = e0.dir().angle();
				double angle1 = e1.dir().angle();
				if (Math.abs(Math.sin(angle0 - angle1)) < 1e-10) {
					segments.remove(e0);
					segments.remove(e1);
					segments.add(e0.isEdge() ? new Edge(v0, v1, a0)
							: new Crease(v0, v1, a0));
					rebuild();
					return true;
				}

			}
		}
		return false;
	}

	private Segment getEdge(
			Collection<Segment> segs) {
		for (Segment seg : segs) {
			if (seg.isEdge()) {
				return seg;
			}

		}
		for (Segment seg : segs) {
			if (seg.getA() == Segment.NONE) {
				return seg;
			}

		}
		return segs.iterator().next();
	}

	private void buildVertices() {
		faces.forEach(face -> {
			face.getVertices().forEach(vertex -> {
				vertices.add(vertex);
			});
			face.getCreases().forEach(crease -> {
				Vertex v0 = crease.getV0();
				Vertex v1 = crease.getV1();
				vertices.add(v0);
				vertices.add(v1);
			});
		});
	}

	private void buildSegments() {
		faces.forEach(face -> {
			face.getEdges().forEach(edge -> {
				segments.add(edge);
			});
			face.getCreases().forEach(crease -> {
				segments.add(crease);
			});

		});
	}

	public void fold(
			Collection<Edge> edges,
			Collection<Crease> creases) {
		build(edges, creases);
		fold();
	}

	public void fold() {
		clearFolded();
		if (baseFace != null) {
			XY c = baseFace.centroid();
			for (Face face : getFaces()) {
				if (face.isInside(c)) {
					this.baseFace = face;
					break;
				}
			}
			this.baseFace = getFaces().get(0);
		} else {
			this.baseFace = getFaces().get(0);
		}
		Edge borderEdge = baseFace.getEdges().get(0);
		buildFolded(baseFace, true, borderEdge);
		implyFaceOrder(0);
		vertices.clear();
		buildVertices();
		segments.clear();
		buildSegments();
	}

	public void clearFolded() {
		faces.forEach(face -> {
			face.isFlip = false;
			face.isFolded = false;
			face.getVertices().forEach(v -> {
				v.initF();
				;
			});
			face.getCreases().forEach(crease -> {
				crease.getV0().initF();
				crease.getV1().initF();
			});

		});
	}

	private void buildFolded(
			Face face,
			boolean prevFaceFlip,
			Edge foldedEdge) {
		if (face.isFolded || face == null) {
			return;
		}
		face.isFlip = !prevFaceFlip;
		face.isFolded = true;
		XY v0f = foldedEdge.getV0().f;
		XY v0 = foldedEdge.getV0();
		Dir x = foldedEdge.dir();
		Dir xf = foldedEdge.dirF();
		Dir y = x.perp();
		Dir yf = xf.perp();
		face.getVertices().forEach(vertex -> {
			vertex.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
		});
		face.getCreases().forEach(crease -> {
			Vertex w0 = crease.getV0();
			Vertex w1 = crease.getV1();
			w0.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
			w1.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
		});

		face.getEdges().forEach(edge -> {
			buildFolded(edge.getPair(face), !prevFaceFlip, edge);
		});

	}

	public void implyFaceOrder(
			int stackcount) {
		boolean retry = false;
		for (int i = 0; i < faces.size(); i++) {
			if (swapFaceOrder(faces.get(i))) {
				retry = true;
				break;
			}
		}
		if (retry && stackcount < 100) {
			implyFaceOrder(stackcount + 1);
		}
		return;
	}

	public boolean swapFaceOrder(
			Face face) {
		int i = faces.indexOf(face);
		Face fi = faces.get(i);
		boolean flip = fi.isFlip;
		for (Edge edge : fi.getEdges()) {
			Face fj = edge.getPair(fi);
			boolean isValley = edge.isValley();
			if (fj == null) {
				continue;
			}
			int j = faces.indexOf(fj);
			if (!(flip ^ isValley) && i < j
					|| flip ^ isValley && j < i) {
				Collections.swap(faces, i, j);
				return true;
			}
		}
		return false;
	}

	public Set<Vertex> getVertices() {
		return vertices;
	}

	public Set<Segment> getSegments() {
		return segments;
	}

	public Face getBaseFace() {
		return baseFace;
	}

}
