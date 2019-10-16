/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.PaintContext;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class NearestFaceFinder {
    public static OriFace findAround(
            PaintContext context) {
        Double point = context.getCurrentLogicalMousePoint();
        OriVertex orivertex = new OriVertex(point.x, point.y);
        for (OriFace face : context.palette.getDiagram().getCp().getOriModel()
                .getFaces()) {
            if (OriFaceUtil.onFace(face, orivertex)) {
                return face;
            }
        }
        return null;
    }
}
