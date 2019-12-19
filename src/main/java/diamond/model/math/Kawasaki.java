/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.math;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.Point2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class Kawasaki {
    public static boolean isValid(Vertex v) {
        double oddSum = 0;
        LinkedList<HalfEdge> halfEdges = v.getHalfEdges();
        boolean isOdd = false;
        Point2D.Double o = v;
        Point2D.Double p0 = null;
        for (HalfEdge he : halfEdges) {
            EdgeType type = he.getType();
            if (type == EdgeType.CUT) {
                return true;
            }
            if (!EdgeType.isSettled(type)) {
                continue;
            }
            if (p0 == null) {
                p0 = he.getV1();
                continue;
            }
            Point2D.Double p1 = Point2DUtil.sub(he.getV1(), o);
            if (isOdd) {
                oddSum += Point2DUtil.angle(p0, p1);
            }
            p0 = p1;
            isOdd = !isOdd;
        }
        if (Fuzzy.around(oddSum, Math.PI)) {
            return false;
        }
        return true;
    }
}
