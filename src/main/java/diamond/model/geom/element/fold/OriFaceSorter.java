/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.fold;

import java.util.LinkedList;

import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class OriFaceSorter {
    private static int debugCount = 0;;

    public static LinkedList<OriFace> sort(LinkedList<OriFace> faces) {
        debugCount = 0;
        validate(faces);
        return faces;
    }

    private static void validate(LinkedList<OriFace> faces) {
        debugCount++;
        if (debugCount > 5000) {
            return;
        }
        for (int i = 0; i < faces.size() - 1; i++) {
            for (int j = faces.size() - 1; j > i; j--) {
                OriFace f0 = faces.get(i);
                OriFace f1 = faces.get(j);
                if (OriFaceComparator.tryCompare(f0, f1, null) == 1) {
                    swap(i, j, faces);
                    validate(faces);
                    return;
                }
            }
        }
        return;
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
