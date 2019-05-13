/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.util.LinkedList;

import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class OriFaceSorter {

    public static LinkedList<OriFace> sort(LinkedList<OriFace> faces) {
        validate(faces);
        return faces;
    }

    private static void validate(LinkedList<OriFace> faces) {
        for (int i = 0; i < faces.size() - 1; ++i) {
            for (int j = i + 1; j < faces.size(); ++j) {
                OriFace f0 = faces.get(i);
                OriFace f1 = faces.get(j);
                if (OriFaceComparator.tryCompare(f0, f1, null) == 1) {
                    liftFace(i, j, faces);
                    validate(faces);
                    return;
                }
            }
        }
        return;
    }

    private static void liftFace(int i, int j,
            LinkedList<OriFace> faces) {
        swap(i, j, faces);

        //        OriFace f1 = faces.get(j);
        //        for (int k = 1; k < j - i - 1; ++k) {
        //            int l = j - k;
        //            OriFace f2 = faces.get(l);
        //            switch (OriFaceComparator.tryCompare(f2, f1, null)) {
        //            case 1:
        //                liftFace(i, l, faces);
        //                break;
        //            case -1:
        //            default:
        //                faces.remove(f1);
        //                faces.add(l, f1);
        //                break;
        //            }
        //        }
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
