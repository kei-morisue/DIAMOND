/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.fold;

import java.util.ArrayList;
import java.util.List;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.FaceUtil;

/**
 * @author Kei Morisue
 *
 */
public class FaceOrderEstimator {

    static void reOrder(Cp cp) {
        ArrayList<Face> copy = new ArrayList<Face>(cp.getFaces());
        for (Face f0 : copy) {
            for (HalfEdge h : f0.getHalfEdges()) {
                Face f1 = h.getPair().getFace();
                FaceOrderEstimator.swapFaces(cp.getFaces(), f1, f0,
                        h.getType());
            }
        }
    }

    static void swapFaces(List<Face> faces, Face f1, Face f0,
            EdgeType type) {
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
