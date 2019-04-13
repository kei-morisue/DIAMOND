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
    int res = 0;

    public OriFaceComparator(List<OriFace> faces) {
        this.faces.addAll(faces);
    }

    @Override
    public int compare(OriFace f0, OriFace f1) {
        for (OriFace oriFace : faces) {
            oriFace.footPrint = false;
        }
        res = 0;
        tryCompare(f0, f1, null);
        return res;
    }

    /**
     * @param f0
     * @param f1
     */
    private void tryCompare(OriFace f0, OriFace f1, LineType type) {
        for (OriHalfEdge he1 : f0.getHalfEdges()) {
            if (he1.getPair().getFace() == f1 && he1.getType() != type) {
                determine(f0, he1);
            }
        }
        f0.footPrint = true;

        for (OriHalfEdge halfEdge : f0.getHalfEdges()) {
            if (halfEdge.getType() == LineType.CUT) {
                continue;
            }
            OriFace face = halfEdge.getPair().getFace();
            if (face.footPrint) {
                continue;
            }
            if (halfEdge.getType() == type) {
                continue;
            }
            tryCompare(face, f1, halfEdge.getType());
        }
    }

    private void determine(OriFace f1, OriHalfEdge he1) {
        switch (he1.getType()) {
        case MOUNTAIN:
        case AUX_MOUNTAIN:
            res = (f1.isFaceFront()) ? 1 : -1;
            break;
        case VALLEY:
        case AUX_VALLEY:
            res = (f1.isFaceFront()) ? -1 : 1;
            break;
        default:
            System.out.println("Irregular face adjacent");
            break;
        }
    }
}
