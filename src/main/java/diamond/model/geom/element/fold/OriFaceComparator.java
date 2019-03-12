/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.util.Comparator;

import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;

/**
 * @author long_
 *
 */
public class OriFaceComparator implements Comparator<OriFace> {

    @Override
    public int compare(OriFace f1, OriFace f2) {
        for (OriHalfEdge he1 : f1.getHalfEdges()) {
            for (OriHalfEdge he2 : f2.getHalfEdges()) {
                if (he1.getPair() == he2) {
                    switch (he1.getType()) {
                    case MOUNTAIN:
                        if (f1.isFaceFront()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    case VALLEY:
                        if (f1.isFaceFront()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    default:
                        System.out.println(
                                "some faces are sharing lines are not moountain / valley.");
                        break;
                    }
                }
            }
        }
        return 0;
    }
}
