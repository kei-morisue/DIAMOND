/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class NearestFaceFinder {
    public static OriFace findAround(Context context) {
        Double point = context.getPaintScreenContext()
                .getCurrentLogicalMousePoint();
        OriVertex orivertex = new OriVertex(point.x, point.y);
        for (OriFace face : context.getPalette().getDiagram().getCp()
                .getOriModel()
                .getFaces()) {
            if (OriFaceUtil.onFace(face, orivertex)) {
                return face;
            }
        }
        return null;
    }
}
