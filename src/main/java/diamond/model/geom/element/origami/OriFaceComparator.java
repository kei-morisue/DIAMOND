/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import diamond.model.geom.element.LineType;

/**
 * @author long_
 *
 */
public class OriFaceComparator implements Comparator<OriFace> {
    private List<OriFace> faces = new ArrayList<>();
    private int res = 0;

    public OriFaceComparator(List<OriFace> faces) {
        this.faces.addAll(faces);
    }

    @Override
    public int compare(OriFace f0, OriFace f1) {
        initialize();
        tryCompare(f0, f1, null);
        if (res == 0) {
            for (OriFace face : faces) {
                initialize();
                tryCompare(f0, face, null);
                if (res == 0) {
                    initialize();
                    tryCompare(face, f1, null);
                    if (res != 0) {
                        return res;
                    }
                }
            }
        }
        return res;
    }

    private void tryCompare(OriFace f0, OriFace f1, LineType type) {
        for (OriHalfEdge he : f0.getHalfEdges()) {
            if (he.getPair().getFace() == f1 && he.getType() != type) {
                determine(f0, he);
                return;
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
            tryCompare(face, f1, he.getType());
            if (res != 0) {
                return;
            }
        }
    }

    private void initialize() {
        for (OriFace oriFace : faces) {
            oriFace.footPrint = false;
        }
        res = 0;
    }

    private void determine(OriFace f1, OriHalfEdge he1) {
        switch (he1.getType()) {
        case MOUNTAIN:
        case UNSETTLED_MOUNTAIN:
            res = (f1.isFaceFront()) ? -1 : 1;
            break;
        case VALLEY:
        case UNSETTLED_VALLEY:
            res = (f1.isFaceFront()) ? 1 : -1;
            break;
        default:
            System.out.println("Irregular face adjacent");
            break;
        }
    }
}
