/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.fold.Folder;
import diamond.model.math.Fuzzy;
import diamond.model.math.NormComparator;
import diamond.view.resource.string.Labels;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeAdder {
	public static void add(
			Context context,
			Point2D.Double p0,
			Point2D.Double p1) {
		EdgeType type = context.getInputType();
		Cp cp = context.getCp();
		addImpl(p0, p1, type, cp);
		addFollowing(context, p0, p1, type, cp);
	}

	private static void addFollowing(
			Context context,
			Point2D.Double p0,
			Point2D.Double p1,
			EdgeType type,
			Cp cp) {
		Vector<Cp> cps = context.getPalette().getCps();
		int i0 = cps.indexOf(cp);
		if (i0 == cps.size() - 1) {
			return;
		}
		if (EdgeType.isSettled(type)) {
			return;
		}
		if (JOptionPane.showConfirmDialog(context.getPaintScreen(),
				Labels.get("across_line")) != 0) {
			return;
		}
		for (int i = i0 + 1; i < cps.size(); i++) {
			Cp cp1 = cps.get(i);
			addImpl(p0, p1, EdgeType.CREASE, cp1);
			Folder.fold(cp1);
		}
	}

	public static void addImpl(
			Point2D.Double p0,
			Point2D.Double p1,
			EdgeType type,
			Cp cp) {
		for (Face face : cp.getFaces()) {
			ArrayList<HalfEdge> externals = CrossPointUtil.splitOutLines(face,
					p0, p1);
			ArrayList<Vertex> internals = CrossPointUtil
					.splitUnsettledLines(face, p0, p1);
			buildHalfEdges(externals, internals, face, type);
		}
	}

	private static void buildHalfEdges(
			ArrayList<HalfEdge> externals,
			ArrayList<Vertex> internals,
			Face face,
			EdgeType type) {
		if (externals.size() == 0) {
			if (internals.size() == 0) {
				return;
			}
			internals.sort(new NormComparator(internals.get(0)));
			HalfEdgeConnector.connect(face, internals, type);
			return;
		}
		Vertex v0 = externals.get(0).getV0();
		internals.add(0, v0);
		if (externals.size() == 2) {
			Vertex v1 = externals.get(1).getV0();
			internals.add(v1);
			internals.sort(new NormComparator(v0));
		}
		internals.sort(new NormComparator(internals.get(0)));
		HalfEdgeConnector.connect(face, internals, type);
	}

	public static Vertex inside(
			Face face,
			Point2D.Double p0,
			Point2D.Double p1) {
		if (FaceUtil.onFace(face, p0)) {
			return find(face, p0);
		}
		if ((FaceUtil.onFace(face, p1))) {
			return find(face, p1);
		}
		return null;
	}

	private static Vertex find(
			Face face,
			Point2D.Double p) {
		for (HalfEdge halfEdge : face.getUnsettledLines()) {
			Vertex v0 = halfEdge.getV0();
			Vertex v1 = halfEdge.getV1();
			if (Fuzzy.around(v0.distance(p), 0.0)) {
				return v0;
			}
			if (Fuzzy.around(v1.distance(p), 0.0)) {
				return v1;
			}
		}
		return new Vertex(p);
	}
}
