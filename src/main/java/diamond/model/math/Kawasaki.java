/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.math;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import diamond.Config;
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
        LinkedList<HalfEdge> edges = v.getHalfEdges();
        int odd = 0;
        for (int i = 0; i < edges.size(); i++) {
            HalfEdge e = edges.get(i);
            if (e.getType() == EdgeType.CUT) {
                return true;
            }

            Point2D.Double preP = edges.get(i).getPair().getV0();
            Point2D.Double nxtP = edges
                    .get((i + 1) % edges.size()).getPair().getV0();

            nxtP = Point2DUtil.sub(nxtP, v);
            preP = Point2DUtil.sub(preP, v);

            if (i % 2 == odd) {
                oddSum += Point2DUtil.angle(preP, nxtP);
            } else {
            }
        }
        if (Math.abs(Math.sin(oddSum - Math.PI)) > Config.EPSILON_RADIAN) {
            return false;
        }
        return true;
    }
}
