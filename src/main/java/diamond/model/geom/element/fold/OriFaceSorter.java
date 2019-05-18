/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.util.LinkedList;

import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.util.OriFaceUtil;

/**
 * @author long_
 *
 */
public class OriFaceSorter {
    private static int debugCount = 0;;

    public static LinkedList<OriFace> sort(LinkedList<OriFace> faces) {
        debugCount = 0;
        while (!validate(faces)) {
            debugCount++;
        }
        return faces;
    }

    private static boolean validate(LinkedList<OriFace> faces) {
        if (debugCount > 1000) {
            return true;
        }
        for (int i = 0; i < faces.size() - 1; i++) {
            for (int j = faces.size() - 1; j > i; j--) {
                OriFace f0 = faces.get(i);
                OriFace f1 = faces.get(j);
                if (OriFaceComparator.tryCompare(f0, f1, null) == 1) {
                    swap(i, j, faces);
                    return false;
                }
                if (validateCollision(i, j, f0, f1, faces)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validateCollision(
            int i, int j, OriFace f0,
            OriFace f1, LinkedList<OriFace> faces) {
        boolean result = false;
        int i0 = i;
        int j0 = j;
        for (OriHalfEdge he : f0.getHalfEdges()) {
            if (he.getPair().getFace() == f1) {
                for (int k = i0 + 1; k < j0 && i0 < k; k++) {
                    OriFace f2 = faces.get(k);
                    if (!OriFaceUtil.offFace(f2, he)) {
                        if (Math.random() > 0.5) {
                            swap(k, i0, faces);
                            result = true;
                            i0++;
                            k++;
                        } else {
                            swap(k, j0, faces);
                            result = true;
                            k--;
                            j0--;
                        }
                    }
                }
                return result;
            }
        }
        return result;
    }

    private static void swap(int i, int j, LinkedList<OriFace> faces) {
        OriFace f0 = faces.get(i);
        OriFace f1 = faces.get(j);
        faces.remove(i);
        faces.add(i, f1);
        faces.remove(j);
        faces.add(j, f0);
    }

}
