/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.fold;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.FaceUtil;

/**
 * @author Kei Morisue
 *
 */
public class FaceOrderEstimator {

	public static void infer(Cp cp0, Cp cp) {
		HashMap<Face, Face> faceMap = new HashMap<Face, Face>();
		LinkedList<Face> faces = cp.getFaces();
		LinkedList<Face> faces0 = cp0.getFaces();
		faces.forEach(face -> {
			Double c = CenterPointUtil.get(face);
			for (Face face0 : faces0) {
				if (FaceUtil.isInside(face0, c)) {
					faceMap.put(face, face0);
					return;
				}

			}
			faceMap.put(face, null);
		});
		boolean flip = faceMap.get(cp.getBaseFace()).isFaceFront();
		faces.sort(new OrderInferrer(faces0, faceMap, flip));
		FaceOrderEstimator.reOrder(cp);
	}

	private static class OrderInferrer implements Comparator<Face> {

		@Override
		public int compare(Face f0, Face f1) {
			Face g0 = faceMap.get(f0);
			Face g1 = faceMap.get(f1);
			if (g0 == null || g1 == null) {
				return 0;
			}
			int i0 = faces0.indexOf(g0);
			int i1 = faces0.indexOf(g1);
			return flip ? i1 - i0 : i0 - i1;
		}

		private LinkedList<Face> faces0;
		private HashMap<Face, Face> faceMap;
		boolean flip;

		public OrderInferrer(LinkedList<Face> faces0, HashMap<Face, Face> faceMap, boolean flip) {
			super();
			this.faces0 = faces0;
			this.faceMap = faceMap;
			this.flip = flip;
		}

	}

	static void reOrder(Cp cp) {
		ArrayList<Face> copy = new ArrayList<Face>(cp.getFaces());
		for (Face f0 : copy) {
			for (HalfEdge h : f0.getSortedEdges()) {
				Face f1 = h.getPair().getFace();
				FaceOrderEstimator.swapFaces(cp.getFaces(), f1, f0, h.getType());
			}
		}
	}

	static void swapFaces(List<Face> faces, Face f1, Face f0, EdgeType type) {
		boolean isFaceFront0 = f0.isFaceFront();
		int i0 = faces.indexOf(f0);
		int i1 = faces.indexOf(f1);

		if (isFaceFront0) {
			if (type == EdgeType.MOUNTAIN) {
				if (i1 < i0) {
					FaceUtil.lift(faces, f1, f0);
				}
			} else if (type == EdgeType.VALLEY) {
				if (i0 < i1) {
					FaceUtil.insert(faces, f1, f0);
				}
			}
		} else {
			if (type == EdgeType.MOUNTAIN) {
				if (i0 < i1) {
					FaceUtil.insert(faces, f1, f0);
				}
			} else if (type == EdgeType.VALLEY) {
				if (i1 < i0) {
					FaceUtil.lift(faces, f1, f0);
				}
			}
		}
	}

}
