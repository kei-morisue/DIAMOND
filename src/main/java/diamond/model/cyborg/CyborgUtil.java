/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.Point2D;
import java.util.Collection;

/**
 * @author Kei Morisue
 *
 */
public class CyborgUtil {

    public static Point2D.Double getCenterPoint(Face face) {
        Collection<HalfEdge> halfEdges = face.getHalfEdges();
        Point2D.Double centerP = new Point2D.Double();
        for (HalfEdge he : halfEdges) {
            centerP = Point2DUtil.plus(centerP, he.getV0());
        }
        centerP = Point2DUtil.scale(centerP, 1.0 / halfEdges.size());
        return centerP;
    }

    public static Point2D.Double getCenterPoint(Vertex v0, Vertex v1) {
        return Point2DUtil.scale(Point2DUtil.plus(v0, v1), 0.5);
    }

}
