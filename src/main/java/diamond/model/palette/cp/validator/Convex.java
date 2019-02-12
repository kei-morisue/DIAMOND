/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.validator;

import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.util.MiscGeomUtil;

/**
 * @author long_
 *
 */
public class Convex {
    public static boolean isValid(OriFace face) {
        if (face.getHalfEdges().size() == 3) {
            return true;
        }

        OriHalfEdge baseHe = face.getHalfEdges().get(0);
        boolean baseFlg = MiscGeomUtil.CCWcheck(
                baseHe.getPrev().getSv(),
                baseHe.getSv(), baseHe.getNext().getSv());

        for (int i = 1; i < face.getHalfEdges().size(); i++) {
            OriHalfEdge he = face.getHalfEdges().get(i);
            if (MiscGeomUtil.CCWcheck(he.getPrev().getSv(),
                    he.getSv(),
                    he.getNext().getSv()) != baseFlg) {
                return false;
            }

        }
        return true;
    }
}
