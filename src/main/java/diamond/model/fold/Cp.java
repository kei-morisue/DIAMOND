/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

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
		Edge e0 = v.getEdge(v0);
		Edge e1 = v.getEdge(v1);
		if (trim(v0, v1, e0, e1)) {
			return true;
		}
		Crease c0 = v.getCrease(v0);
		Crease c1 = v.getCrease(v1);
		if (trim(v0, v1, c0, c1)) {
			return true;
		}
		return false;
	}

	private boolean trim(
			Vertex v0,
			Vertex v1,
			Segment s0,
			Segment s1) {
		if (s0 != null && s1 != null) {
			int a0 = s0.getA();
			if (a0 == s1.getA()) {
				double angle0 = s0.dir().angle();
				double angle1 = s1.dir().angle();
				if (Math.abs(Math.sin(angle0 - angle1)) < Geo.RADIAN_EPS) {
					segments.remove(s0);
					segments.remove(s1);
					segments.add(s0.isEdge() ? new Edge(v0, v1, a0)
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
			face.forVertices(vertex -> {
				vertices.add(vertex);
			});
			face.forCreases(crease -> {
				Vertex v0 = crease.getV0();
				Vertex v1 = crease.getV1();
				vertices.add(v0);
				vertices.add(v1);
			});
		});
	}

	private void buildSegments() {
		faces.forEach(face -> {
			face.forEdges(edge -> {
				segments.add(edge);
			});
			face.forCreases(crease -> {
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
		this.baseFace = buildBaseFace();
		Edge borderEdge = baseFace.getBaseEdge();
		buildFolded(baseFace, true, borderEdge);
		implyFaceOrder(0);
		vertices.clear();
		buildVertices();
		segments.clear();
		buildSegments();
	}

	private Face buildBaseFace() {
		if (baseFace != null) {
			XY c = baseFace.centroid();
			for (Face face : faces) {
				if (face.isInside(c)) {
					return face;
				}
			}
			return faces.get(0);
		}
		return faces.get(0);
	}

	public void clearFolded() {
		faces.forEach(face -> {
			face.isFlip = false;
			face.isFolded = false;
			face.forVertices(v -> {
				v.initF();
				;
			});
			face.forCreases(crease -> {
				crease.getV0().initF();
				crease.getV1().initF();
			});

		});
	}

	private void buildFolded(
			Face face,
			boolean prevFaceFlip,
			Edge foldedEdge) {
		if (face.isFolded) {
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
		face.forVertices(vertex -> {
			vertex.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
		});
		face.forCreases(crease -> {
			Vertex w0 = crease.getV0();
			Vertex w1 = crease.getV1();
			w0.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
			w1.setF(prevFaceFlip, v0f, v0, x, xf, y, yf);
		});

		face.forEdges(edge -> {
			buildFolded(edge.getPair(face), !prevFaceFlip, edge);
		});

	}

	public void implyFaceOrder(
			int stackcount) {
		boolean retry = false;
		for (int i = 0; i < faces.size(); i++) {
			if (faces.get(i).swapWrongPair(faces)) {
				retry = true;
				break;
			}
		}
		if (retry && stackcount < 100) {
			implyFaceOrder(stackcount + 1);
		}
		return;
	}

	public Vertex find(
			XY p,
			double epsSq) {
		return Finder.find(vertices, p, epsSq);
	}

	public boolean add(
			Segment segment) {
		Vertex v0 = segment.getV0();
		Vertex v1 = segment.getV1();
		if (Geo.isClose(v0, v1, eps)) {
			return false;
		}
		segments.add(segment);
		rebuild();
		return true;
	};

	public boolean remove(
			Segment segment) {
		int a = segment.getA();
		if (a == Segment.NONE && segment.isEdge()) {
			return false;
		}
		if (segments.remove(segment)) {
			rebuild();
			int i = size();
			int itr = i * i;
			while (trim() || itr < 0) {
				itr--;
			}
			return true;
		}
		return false;

	};

	public void forVertices(
			Consumer<Vertex> action) {
		vertices.forEach(action);
	}

	public Set<Segment> getSegments() {
		return segments;
	}

	public Face getBaseFace() {
		return baseFace;
	}

}
