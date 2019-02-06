/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.validator;

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
        Vector2d p = v.p;
        double oddSum = 0;
        for (int i = 0; i < v.edges.size(); i++) {
            OriEdge e = v.edges.get(i);
            if (e.type == LineType.CUT) {
                return true;
            }

            Vector2d preP = new Vector2d(
                    v.edges.get(i).oppositeVertex(v).p);
            Vector2d nxtP = new Vector2d(v.edges
                    .get((i + 1) % v.edges.size()).oppositeVertex(v).p);

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
