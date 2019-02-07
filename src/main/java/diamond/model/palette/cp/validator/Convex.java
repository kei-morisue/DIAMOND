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
        if (face.halfEdges.size() == 3) {
            return true;
        }

        OriHalfEdge baseHe = face.halfEdges.get(0);
        boolean baseFlg = MiscGeomUtil.CCWcheck(
                baseHe.getPrev().getVertex().getP(),
                baseHe.getVertex().getP(), baseHe.getNext().getVertex().getP());

        for (int i = 1; i < face.halfEdges.size(); i++) {
            OriHalfEdge he = face.halfEdges.get(i);
            if (MiscGeomUtil.CCWcheck(he.getPrev().getVertex().getP(),
                    he.getVertex().getP(),
                    he.getNext().getVertex().getP()) != baseFlg) {
                return false;
            }

        }
        return true;
    }
}
