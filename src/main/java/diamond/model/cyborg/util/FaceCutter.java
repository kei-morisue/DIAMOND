/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceCutter {
    public static void cut(Face f, HalfEdge splitter) {
        if (!EdgeType.isSettled(splitter.getType())) {
            f.addUnsettled(splitter);
        } else {
            Vertex c = splitter.getV0();
            HalfEdge h0 = open(f, c);
            HalfEdge h1 = open(f, c);
            h0.connectTo(splitter);
            HalfEdge hP = splitter.getPair();
            splitter.connectTo(hP);
            hP.connectTo(h1);
            f.add(splitter);
            f.add(hP);
        }
        ArrayList<Vertex> cps = FaceSplitter.splitUnsettledLines(f, splitter);
        HalfEdgeSplitter.split(splitter, cps);
    }

    private static HalfEdge open(Face face, Vertex c) {
        for (HalfEdge he : face.getHalfEdges()) {
            if (he.getV1() == c) {
                return he;
            }
        }
        return null;
    }
}
