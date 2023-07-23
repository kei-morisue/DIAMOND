/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.fold.Folder;
import diamond.view.ui.screen.ScreenTransform;

/**
 * @author Kei Morisue
 *
 */
public class CpBuilder {
	public static Cp buildSquare() {
		Cp cp = new Cp();
		double size = Config.PAPER_SIZE;
		Vertex v0 = new Vertex(size, size);
		Vertex v1 = new Vertex(-size, size);
		Vertex v2 = new Vertex(-size, -size);
		Vertex v3 = new Vertex(size, -size);

		HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
		HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
		HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
		HalfEdge he3 = new HalfEdge(v3, v0, EdgeType.CUT);

		Face f0 = new Face();
		cp.getFaces().add(f0);
		f0.add(he0);
		f0.add(he1);
		f0.add(he2);
		f0.add(he3);

		he0.connectTo(he1);
		he1.connectTo(he2);
		he2.connectTo(he3);
		he3.connectTo(he0);

		he0.getPair().connectTo(he3.getPair());
		he3.getPair().connectTo(he2.getPair());
		he2.getPair().connectTo(he1.getPair());
		he1.getPair().connectTo(he0.getPair());
		return cp;
	}

	public static Cp buildNext(Context context, Cp cp0) {
		Cp cp1 = copyCp(cp0);

		Folder.fold(cp1);

		return cp1;
	}

	public static Cp buildUnfoldedNext(Context context, Cp cp0) {
		Cp cp1 = copyCp(cp0);
		cp1.getFaces().forEach(face -> {
			face.getUnsettledLines().forEach(he -> {
				EdgeType type = he.getType();
				if (type == EdgeType.UNSETTLED_MOUNTAIN || type == EdgeType.UNSETTLED_VALLEY) {
					he.setType(EdgeType.CREASE);
				}
			});
		});
		Folder.fold(cp1);

		return cp1;
	}

	public static Cp buildFoldedNext(Context context, Cp cp0) {
		Cp cp1 = copyCp(cp0);
		ArrayDeque<HalfEdge> queue = new ArrayDeque<HalfEdge>();
		cp1.getFaces().forEach(face -> {
			face.getUnsettledLines().forEach(he -> {
				EdgeType type = he.getType();
				if (type == EdgeType.UNSETTLED_MOUNTAIN || type == EdgeType.UNSETTLED_VALLEY) {
					queue.add(he);
				}
			});

		});
		settle(queue, cp1);
		Folder.fold(cp1);

		return cp1;
	}

	private static void settle(ArrayDeque<HalfEdge> queue, Cp cp) {
		HalfEdge failed = null;
		HashSet<HalfEdge> settled = new HashSet<HalfEdge>();
		while (queue.size() > 0 && (queue.peek() != failed)) {
			HalfEdge he = queue.poll();
			if (settled.contains(he.getPair())) {
				continue;
			}
			boolean is_settled = HalfEdgeModifier.settle(cp, he);
			if (!is_settled) {
				queue.add(he);
				if (failed == null) {
					failed = he;
				}

				continue;
			}
			failed = null;
			settled.add(he);
			System.out.println(he);
			System.out.println(queue.size());
		}
	}

	public static Cp buildHex() {
		Cp cp = new Cp();
		double size = Config.PAPER_SIZE;
		Vertex v0 = new Vertex(size, 0);
		double r3 = Math.sqrt(3);
		Vertex v1 = new Vertex(size / 2, size * r3 / 2);
		Vertex v2 = new Vertex(-size / 2, size * r3 / 2);
		Vertex v3 = new Vertex(-size, 0);
		Vertex v4 = new Vertex(-size / 2, -size * r3 / 2);
		Vertex v5 = new Vertex(size / 2, -size * r3 / 2);

		HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
		HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
		HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
		HalfEdge he3 = new HalfEdge(v3, v4, EdgeType.CUT);
		HalfEdge he4 = new HalfEdge(v4, v5, EdgeType.CUT);
		HalfEdge he5 = new HalfEdge(v5, v0, EdgeType.CUT);

		Face f0 = new Face();
		cp.getFaces().add(f0);
		f0.add(he0);
		f0.add(he1);
		f0.add(he2);
		f0.add(he3);
		f0.add(he4);
		f0.add(he5);

		he0.connectTo(he1);
		he1.connectTo(he2);
		he2.connectTo(he3);
		he3.connectTo(he4);
		he4.connectTo(he5);
		he5.connectTo(he0);

		he0.getPair().connectTo(he5.getPair());
		he5.getPair().connectTo(he4.getPair());
		he4.getPair().connectTo(he3.getPair());
		he3.getPair().connectTo(he2.getPair());
		he2.getPair().connectTo(he1.getPair());
		he1.getPair().connectTo(he0.getPair());

		return cp;
	}

	public static Cp build15() {
		Cp cp = new Cp();
		double size = Config.PAPER_SIZE;
		Vertex v0 = new Vertex(size, size);
		double a = size * (2 - Math.sqrt(3));
		Vertex v1 = new Vertex(a, size);
		Vertex v2 = new Vertex(-size, size);
		Vertex v3 = new Vertex(-size, -size);
		Vertex v4 = new Vertex(-a, -size);
		Vertex v5 = new Vertex(size, -size);

		HalfEdge he0 = new HalfEdge(v0, v1, EdgeType.CUT);
		HalfEdge he1 = new HalfEdge(v1, v2, EdgeType.CUT);
		HalfEdge he2 = new HalfEdge(v2, v3, EdgeType.CUT);
		HalfEdge he3 = new HalfEdge(v3, v4, EdgeType.CUT);
		HalfEdge he4 = new HalfEdge(v4, v5, EdgeType.CUT);
		HalfEdge he5 = new HalfEdge(v5, v0, EdgeType.CUT);

		Face f0 = new Face();
		cp.getFaces().add(f0);
		f0.add(he0);
		f0.add(he1);
		f0.add(he2);
		f0.add(he3);
		f0.add(he4);
		f0.add(he5);

		he0.connectTo(he1);
		he1.connectTo(he2);
		he2.connectTo(he3);
		he3.connectTo(he4);
		he4.connectTo(he5);
		he5.connectTo(he0);

		he0.getPair().connectTo(he5.getPair());
		he5.getPair().connectTo(he4.getPair());
		he4.getPair().connectTo(he3.getPair());
		he3.getPair().connectTo(he2.getPair());
		he2.getPair().connectTo(he1.getPair());
		he1.getPair().connectTo(he0.getPair());

		return cp;
	}

	private static Cp copyCp(Cp cp0) {
		Cp cp1 = new Cp();
		cp1.setTransform(new ScreenTransform(cp0.getTransform()));
		HashMap<Vertex, Vertex> vMap = new HashMap<Vertex, Vertex>();
		for (Face f0 : cp0.getFaces()) {
			Face f1 = buildFace(vMap, f0);
			cp1.add(f1);
			if (f0 == cp0.buildBaseFace()) {
				cp1.setBaseFace(f1);
			}
		}

		return cp1;
	}

	private static Face buildFace(HashMap<Vertex, Vertex> vMap, Face f0) {
		Face f1 = new Face();
		f1.getProperty().setDisabled(f0.getProperty().isDisabled());
		for (HalfEdge he : f0.getUnsettledLines()) {
			HalfEdge he1 = buildHalfEdge(vMap, he);
			f1.add(he1);
		}
		ArrayList<HalfEdge> halfEdges = new ArrayList<HalfEdge>();
		for (HalfEdge he : f0.getSortedEdges()) {
			HalfEdge he1 = buildHalfEdge(vMap, he);
			f1.add(he1);
			halfEdges.add(he1);
		}
		int size = halfEdges.size();
		for (int i = 0; i < size; i++) {
			halfEdges.get(i).connectTo(halfEdges.get((i - 1 + size) % (size)));
			halfEdges.get((i + 1) % (size)).connectTo(halfEdges.get(i));
		}
		return f1;
	}

	private static HalfEdge buildHalfEdge(HashMap<Vertex, Vertex> vMap, HalfEdge he) {
		Vertex v0 = buildVertex(vMap, he.getV0());
		Vertex v1 = buildVertex(vMap, he.getV1());
		HalfEdge he1;
		if (v0.getConnection(v1) == null) {
			he1 = new HalfEdge(v0, v1, he.getType());
		} else {
			he1 = v0.getConnection(v1);
		}
		return he1;
	}

	private static Vertex buildVertex(HashMap<Vertex, Vertex> vMap, Vertex v) {
		if (!vMap.containsKey(v)) {
			Vertex v0 = new Vertex(v);
			v0.setOffset(new Point2D.Double(0.0, 0.0));
			vMap.put(v, v0);
			return v0;

		} else {
			return vMap.get(v);
		}
	}

}