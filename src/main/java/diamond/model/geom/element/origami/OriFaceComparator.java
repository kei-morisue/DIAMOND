/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.util.Comparator;
import java.util.List;

/**
 * @author long_
 *
 */
public class OriFaceComparator implements Comparator<OriFace> {
    List<OriFace> faces;

    public OriFaceComparator(List<OriFace> faces) {
        this.faces = faces;
    }

    @Override
    public int compare(OriFace f1, OriFace f2) {
        for (OriHalfEdge he1 : f1.getHalfEdges()) {
            for (OriHalfEdge he2 : f2.getHalfEdges()) {
                if (he1.getPair() == he2) {
                    switch (he1.getType()) {
                    case MOUNTAIN:
                    case AUX_MOUNTAIN:
                        if (f1.isFaceFront()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    case VALLEY:
                    case AUX_VALLEY:
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
