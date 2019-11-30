/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;

/**
 * @author Kei Morisue
 *
 */
public class FaceMarger {

    public static Face marge(HalfEdge h0) {
        HalfEdge h1 = h0.getPair();
        Face face = new Face();
        Face f0 = h0.getFace();
        Face f1 = h0.getPair().getFace();
        HalfEdge h0N = h0.getNext();
        HalfEdge h0P = h0.getPrev();
        HalfEdge h1N = h1.getNext();
        HalfEdge h1P = h1.getPrev();

        h1P.connectTo(h0N);
        h0P.connectTo(h1N);

        ArrayList<HalfEdge> halfEdges = face.getHalfEdges();
        halfEdges.addAll(f0.getHalfEdges());
        halfEdges.remove(h0);
        halfEdges.addAll(f1.getHalfEdges());
        halfEdges.remove(h1);

        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
        unsettledLines.add(h0);
        unsettledLines.add(h1);
        unsettledLines.addAll(f0.getUnsettledLines());
        unsettledLines.addAll(f1.getUnsettledLines());
        h0.setFace(face);
        h1.setFace(face);

        return face;
    }

}
