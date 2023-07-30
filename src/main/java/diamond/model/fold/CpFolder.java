/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.util.Collection;
import java.util.List;

import diamond.model.Dir;
import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;

/**
 * @author Kei Morisue
 *
 */
public class CpFolder {
	public static boolean isFoldable(
			Vertex v,
			List<Edge> edges) {
		if (edges.size() == 0) {
			return true;
		}
		if (edges.size() == 2) {
			return edges.get(0).getA() == edges.get(1).getA();
		}
		int nV = 0;
		int nM = 0;
		double angle = 0.0;
		Vertex v0 = edges.get(edges.size() - 1).getPair(v);
		Dir d0 = v.dir(v0);
		for (Edge edge : edges) {
			if (edge.isBoundary()) {
				return true;
			}

			if (edge.isValley()) {
				++nV;
			} else {
				++nM;
			}
			v0 = edge.getPair(v);
			Dir d = v.dir(v0);
			double delta = d.angle() - d0.angle();
			int n = nV + nM;
			angle += (n >> 1 << 1 == n) ? delta : -delta;
			d0 = d;

		}
		if (nV == 0 && nM == 0) {
			return true;
		}
		boolean isMaekawa = Math.abs(nM - nV) == 2;
		boolean isKawasaki = Geo.isClose(Math.abs(angle % (Math.PI * 2)), 0.0,
				Geo.RADIAN_EPS);
		return isMaekawa && isKawasaki;
	}

	public static void buildFolded(
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
			setF(vertex, prevFaceFlip, v0f, v0, x, xf, y, yf);
		});
		face.forCreases(crease -> {
			Vertex w0 = crease.getV0();
			Vertex w1 = crease.getV1();
			setF(w0, prevFaceFlip, v0f, v0, x, xf, y, yf);
			setF(w1, prevFaceFlip, v0f, v0, x, xf, y, yf);
		});

		face.forEdges(edge -> {
			buildFolded(edge.getPair(face), !prevFaceFlip, edge);
		});

	}

	private static void setF(
			Vertex v,
			boolean prevFaceFlip,
			XY v0f,
			XY v0,
			Dir x,
			Dir xf,
			Dir y,
			Dir yf) {
		Dir d = v0.dir(v);
		double cx = x.dot(d) / x.mgSq();
		double cy = y.dot(d) / x.mgSq();
		cy *= (prevFaceFlip) ? 1 : -1;
		v.setF(xf.mul(cx).add(yf.mul(cy)).ver(v0f));
	}

	public static void implyFaceOrder(
			List<Face> faces,
			int stackcount) {
		boolean retry = false;
		for (int i = 0; i < faces.size(); i++) {
			if (faces.get(i).swapWrongPair(faces)) {
				retry = true;
				break;
			}
		}
		if (retry && stackcount > 0) {
			implyFaceOrder(faces, stackcount - 1);
		}
		return;
	}

	public static boolean trim(
			Collection<Vertex> vertices,
			Collection<Segment> segments) {
		for (Vertex vertex : vertices) {
			if (trim(segments, vertex)) {
				return true;
			}
		}
		return false;
	}

	private static boolean trim(
			Collection<Segment> segments,
			Vertex v) {
		Tuple<Vertex> pair = v.getPair();
		if (pair == null) {
			return false;
		}
		Vertex v0 = pair.fst;
		Vertex v1 = pair.snd;
		Edge e0 = v.getEdge(v0);
		Edge e1 = v.getEdge(v1);
		if (trim(v0, v1, segments, e0, e1)) {
			return true;
		}
		Crease c0 = v.getCrease(v0);
		Crease c1 = v.getCrease(v1);
		if (trim(v0, v1, segments, c0, c1)) {
			return true;
		}
		return false;
	}

	private static boolean trim(
			Vertex v0,
			Vertex v1,
			Collection<Segment> segments,
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
					return true;
				}

			}
		}
		return false;
	}
}
