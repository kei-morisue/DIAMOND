/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.util.ArrayList;
import java.util.HashSet;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.NormComparator;

/**
 * @author Kei Morisue
 *
 */
public class FaceSplitter {
    public static Face[] split(Face face, HalfEdge splitter) {
        Face f0 = new Face();
        Face f1 = new Face();
        Vertex v0 = splitter.getV0();
        Vertex v1 = splitter.getV1();
        open(face, f0, f1, splitter);
        if (f0.getHalfEdges().size() != 1 || f1.getHalfEdges().size() != 1) {
            return null;//TODO cut segment, non-convex faces
        }
        close(f0, v0, splitter);
        close(f1, v1, splitter.getPair());
        ArrayList<Vertex> cps = splitUnsettledLines(face, splitter);
        if (!EdgeType.isSettled(splitter.getType())) {
            f0 = FaceMarger.marge(splitter);
        }
        HalfEdgeSplitter.split(splitter, cps);
        Face[] faces = { f0, f1 };
        return faces;
    }

    public static ArrayList<Vertex> splitUnsettledLines(Face face,
            HalfEdge splitter) {
        HashSet<Vertex> crossPoints = new HashSet<Vertex>();
        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
        HashSet<HalfEdge> copy = new HashSet<HalfEdge>();
        copy.addAll(unsettledLines);
        for (HalfEdge he : copy) {
            if (he == splitter || he == splitter.getPair()
                    || he.getProperty().isDisabled()) {
                continue;
            }
            Vertex v = HalfEdgeSplitter.splitUnsettled(splitter, he);
            if (v != null) {
                crossPoints.add(v);
            }
        }
        ArrayList<Vertex> ordered = new ArrayList<Vertex>();
        ordered.addAll(crossPoints);
        ordered.sort(new NormComparator(splitter.getV0()));
        return ordered;
    }

    private static void open(Face face, Face f0, Face f1,
            HalfEdge splitter) {
        Vertex v0 = splitter.getV0();
        Vertex v1 = splitter.getV1();
        for (HalfEdge he : face.getHalfEdges()) {
            if (he.getV0() == v1) {
                f0.add(he);
                splitter.connectTo(he);
            } else if (he.getV0() == v0) {
                f1.add(he);
                splitter.getPair().connectTo(he);
            }
        }
    }

    private static void close(Face face, Vertex endV, HalfEdge splitter) {
        HalfEdge h = face.getHalfEdges().get(0);
        do {
            h = h.getNext();
            face.add(h);
        } while (h.getV1() != endV);
        face.add(splitter);
        h.connectTo(splitter);
    }

}
