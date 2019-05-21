/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;

/**
 * @author long_
 *
 */
public class OriFaceComparator {

    public static int tryCompare(OriFace f0, OriFace f1, LineType type) {
        for (OriHalfEdge he : f0.getHalfEdges()) {
            if (he.getPair().getFace() == f1 && he.getType() != type) {
                return determine(f0, he);
            }
            f0.footPrint = true;
            if (he.getType() == LineType.CUT) {
                continue;
            }
            if (he.getType() == type) {
                continue;
            }
            OriFace face = he.getPair().getFace();
            if (face.footPrint) {
                continue;
            }
            int trial = tryCompare(face, f1, he.getType());
            if (trial != 0) {
                return trial;
            }
        }
        return 0;
    }

    private static int determine(OriFace f1, OriHalfEdge he1) {
        switch (he1.getType()) {
        case MOUNTAIN:
            return (f1.isFaceFront()) ? -1 : 1;
        case VALLEY:
            return (f1.isFaceFront()) ? 1 : -1;
        case NONE:
            return 0;
        default:
            System.out.println("Irregular face adjacent");
            return 0;
        }
    }
}
