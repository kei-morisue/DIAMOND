/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.util.LinkedList;

import diamond.model.geom.element.LineType;

/**
 * @author long_
 *
 */
public class OriFaceSorter {

    public static LinkedList<OriFace> sort(LinkedList<OriFace> faces) {
        LinkedList<OriFace> sorted = new LinkedList<>();
        LinkedList<OriFace> tmpFaces = new LinkedList<>();
        tmpFaces.addAll(faces);
        sorted.add(faces.peek());
        tmpFaces.pop();
        while (!tmpFaces.isEmpty()) {
            add(tmpFaces, sorted);
        }
        return sorted;
    }

    private static void add(LinkedList<OriFace> faces,
            LinkedList<OriFace> sorted) {
        OriFace face = faces.getFirst();
        for (OriFace f1 : sorted) {
            if (insertFace(face, f1, sorted, faces)) {
                return;
            }
        }
        faces.addLast(face);
        faces.pop();
    }

    private static boolean insertFace(OriFace face, OriFace f1,
            LinkedList<OriFace> sorted, LinkedList<OriFace> faces) {
        int result = tryCompare(face, f1, null);
        int index = sorted.indexOf(f1);
        switch (result) {
        case -1:
            insertBehind(face, sorted, faces, index);
            break;
        case 1:
            if (index == sorted.size() - 1) {
                insertBehind(face, sorted, faces, index + 1);
                break;
            }
            f1 = sorted.get(index + 1);
            insertFace(face, f1, sorted, faces);
            break;
        default:
            return false;
        }
        faces.remove(face);
        return true;
    }

    private static void insertBehind(OriFace face, LinkedList<OriFace> sorted,
            LinkedList<OriFace> faces, int index) {
        sorted.add(index, face);
        faces.removeFirst();
    }

    private static int tryCompare(OriFace f0, OriFace f1, LineType type) {
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
        default:
            System.out.println("Irregular face adjacent");
            return 0;
        }
    }
}
