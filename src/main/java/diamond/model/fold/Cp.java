/**
 * DEFOX - Origami Diagram Editor
 * Copyright (C) 2023 Kei Morisue
 */
package diamond.model.fold;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.sun.tools.javac.util.Pair;

import diamond.model.Geo;
import diamond.model.Tuple;
import diamond.model.XY;
import diamond.model.fold.symbol.SymbolBase;

/**
 * @author Kei Morisue
 *
 */
public class Cp extends Flat {
	transient private Set<Vertex> vertices = new HashSet<Vertex>();
	transient private Set<Segment> segments = new HashSet<Segment>();
	private HashMap<Renderable, SymbolBase> symbols = new HashMap<>();
	private Face baseFace;
	public double eps;

	public Cp(Cp cp) {
		super();
		rebuild(cp.segments);
	}

	public Cp(double scale) {
		super();
		CpBuilder.buildSquare(this, scale);
		this.eps = scale / 300;
	}

	public void save(
			CpSave save) {
		try {
			save.save(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void restore(
			CpSave save) {
		Cp restored = save.restore();
		this.faces = restored.faces;
		this.symbols = restored.symbols;
		this.baseFace = restored.baseFace;
		this.eps = restored.eps;
		segments.clear();
		buildSegments();
		rebuild();
	}

	public void rebuild() {
		rebuild(this.segments);
	}

	public void rebuild(
			Collection<Segment> segments) {
		symbols.clear();
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
			Segment seg = getMainSegment(lines);
			seg.add(vv.fst, vv.snd, edges, creases);
		});
		fold(edges, creases);
	}

	private void trim() {
		int itr = vertices.size();
		while (CpFolder.trim(vertices, segments) && itr > 0) {
			rebuild();
			itr--;
		}
	};

	private Segment getMainSegment(
			Collection<Segment> overrappedSegs) {
		for (Segment seg : overrappedSegs) {
			if (seg.isEdge()) {
				return seg;
			}
		}
		for (Segment seg : overrappedSegs) {
			if (seg.getA() != Segment.NONE) {
				return seg;
			}
		}
		return overrappedSegs.iterator().next();
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
		CpFolder.buildFolded(baseFace, true, borderEdge);
		implyFaceOrder();
		vertices.clear();
		buildVertices();
		vertices.forEach(v -> v.setFoldable());
		segments.clear();
		buildSegments();
	}

	public void implyFaceOrder() {
		CpFolder.implyFaceOrder(faces, 100);
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

	public Vertex findVertex(
			XY p,
			double epsSq) {
		return CpFinder.findVertex(vertices, p, epsSq);
	}

	public Segment findSegment(
			XY p,
			double epsSq) {
		return CpFinder.findSegment(segments, p, epsSq);
	}

	public Face findFace(
			XY p) {
		return CpFinder.findFace(faces, p);
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
		trim();
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
			trim();
			return true;
		}
		return false;

	}

	public void distort() {
		vertices.forEach(vertex -> {
			vertex.initD();
		});
	}

	public void distort(
			double k,
			double theta) {
		double s = Math.sin(theta);
		double c = Math.cos(theta);
		vertices.forEach(vertex -> {
			vertex.setD(k * c, -s * k, s * k, k * c);
		});
	}

	public Face getBaseFace() {
		return baseFace;
	}

	public void setBaseFace(
			Face baseFace) {
		this.baseFace = baseFace;
		fold();
	}

	public void put(
			Renderable key,
			SymbolBase symbol) {
		SymbolBase found = symbols.get(key);
		if (found != null) {
			found.flip();
			return;
		}
		symbols.put(key, symbol);
	}

	public SymbolBase removeSymbol(
			Renderable key) {
		SymbolBase removed = symbols.remove(key);
		return removed;
	}

	public HashMap<Face, HashSet<SymbolBase>> getSymbolMap() {
		HashMap<Face, HashSet<SymbolBase>> map = new HashMap<>();
		faces.forEach(face -> {
			map.put(face, new HashSet<SymbolBase>());
		});
		symbols.forEach((
				key,
				symbol) -> {
			Face layer = symbol.getLayer(faces);
			map.get(layer).add(symbol);
		});
		return map;
	}

}
