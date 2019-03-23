/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.validator;

import java.util.LinkedList;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class Kawasaki {
    public static boolean isValid(OriVertex v) {
        double oddSum = 0;
        LinkedList<OriHalfEdge> edges = v.getHalfEdges();
        for (int i = 0; i < edges.size(); i++) {
            OriHalfEdge e = edges.get(i);
            if (e.getType() == LineType.CUT) {
                return true;
            }

            OriVertex preP = edges.get(i).getPair().getSv();
            OriVertex nxtP = edges
                    .get((i + 1) % edges.size()).getPair().getSv();

            nxtP.sub(v);
            preP.sub(v);

            if (i % 2 == 0) {
                oddSum += preP.angle(nxtP);
            } else {
            }
        }
        if (Math.abs(oddSum - Math.PI) > Math.PI / 180 / 2) {
            // System.out.println("edge angle sum invalid");
            return false;
        }
        return true;
    }
}
