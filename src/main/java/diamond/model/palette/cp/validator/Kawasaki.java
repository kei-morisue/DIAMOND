/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.validator;

import java.util.LinkedList;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.orimodel.OriEdge;
import diamond.model.geom.element.orimodel.OriVertex;

/**
 * @author long_
 *
 */
public class Kawasaki {
    public static boolean isValid(OriVertex v) {
        Vector2d p = v.getP();
        double oddSum = 0;
        LinkedList<OriEdge> edges = v.getEdges();
        for (int i = 0; i < edges.size(); i++) {
            OriEdge e = edges.get(i);
            if (e.getType() == LineType.CUT) {
                return true;
            }

            Vector2d preP = new Vector2d(
                    edges.get(i).oppositeVertex(v).getP());
            Vector2d nxtP = new Vector2d(edges
                    .get((i + 1) % edges.size()).oppositeVertex(v).getP());

            nxtP.sub(p);
            preP.sub(p);

            if (i % 2 == 0) {
                oddSum += preP.angle(nxtP);
            } else {
            }
        }
        if (Math.abs(oddSum - Math.PI) > Math.PI / 180 / 2) {
            System.out.println("edge angle sum invalid");
            return false;
        }
        return true;
    }
}
