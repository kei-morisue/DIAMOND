/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.Collection;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class CenterPointUtil {

    public static Point2D.Double get(Face face) {
        Collection<HalfEdge> halfEdges = face.getSortedEdges();
        Point2D.Double centerP = new Point2D.Double();
        for (HalfEdge he : halfEdges) {
            centerP = Point2DUtil.add(centerP, he.getV0());
        }
        centerP = Point2DUtil.scale(centerP, 1.0 / halfEdges.size());
        return centerP;
    }

    public static Point2D.Double get(Point2D.Double v0, Point2D.Double v1) {
        return Point2DUtil.scale(Point2DUtil.add(v0, v1), 0.5);
    }

    public static Point2D.Double get(HalfEdge he) {
        return get(he.getV0(), he.getV1());
    }

}
