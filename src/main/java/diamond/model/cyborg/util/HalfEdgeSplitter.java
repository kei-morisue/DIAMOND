/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeSplitter {
    public static Vertex splitUnsettled(HalfEdge splitter, HalfEdge he) {
        double[] ds = CrossPointUtil.getSplitter(splitter.getV0(),
                splitter.getV1(), he);
        if (ds == null) {
            distrubute(splitter, he);
            return null;
        }
        if (Fuzzy.around(ds[1], 1.0)) {
            distrubute(splitter, he);
            return he.getV1();
        }
        if (Fuzzy.around(ds[1], 0.0)) {
            distrubute(splitter, he);
            return he.getV0();
        }
        Double p = Point2DUtil.split(he.getV0(), he.getV1(), ds[1]);
        Vertex v = new Vertex(p);
        return splitUnsettled(splitter, he, v);
    }

    public static Vertex splitUnsettled(HalfEdge splitter, HalfEdge he,
            Vertex v) {
        HalfEdge h0 = new HalfEdge(he.getV0(), v, he.getType());
        HalfEdge h1 = new HalfEdge(v, he.getV1(), he.getType());
        h0.connectTo(h1);
        HalfEdge h0P = h0.getPair();
        HalfEdge h1P = h1.getPair();
        h1P.connectTo(h0P);
        distrubute(splitter, h0, h1);
        return v;
    }

    private static void distrubute(HalfEdge splitter, HalfEdge he) {
        Face f0 = splitter.getFace();
        Face f1 = splitter.getPair().getFace();
        HalfEdge heP = he.getPair();

        if (FaceUtil.onFace(f0, he)) {
            he.setFace(f0);
            heP.setFace(f0);
            f0.addUnsettled(he);
        } else {
            he.setFace(f1);
            heP.setFace(f1);
            f1.addUnsettled(he);
        }
    }

    private static void distrubute(HalfEdge splitter, HalfEdge h0,
            HalfEdge h1) {
        Face f0 = splitter.getFace();
        Face f1 = splitter.getPair().getFace();
        HalfEdge h0P = h0.getPair();
        HalfEdge h1P = h1.getPair();

        if (FaceUtil.onFace(f0, h0)) {
            h0.setFace(f0);
            h0P.setFace(f0);
            h1.setFace(f1);
            h1P.setFace(f1);
            f0.addUnsettled(h0);
            f1.addUnsettled(h1);
        } else {
            h1.setFace(f0);
            h1P.setFace(f0);
            h0.setFace(f1);
            h0P.setFace(f1);
            f0.addUnsettled(h1);
            f1.addUnsettled(h0);
        }
    }

    public static void split(HalfEdge he, ArrayList<Vertex> splitters) {
        HalfEdge h0 = he;
        for (Vertex v : splitters) {
            split(h0, v);
            h0 = h0.getNext();
        }
    }

    public static Vertex split(HalfEdge he, double t) {
        Double p = Point2DUtil.split(he.getV0(), he.getV1(), t);
        Vertex v = new Vertex(p);
        return split(he, v);
    }

    private static Vertex split(HalfEdge he, Vertex v) {
        HalfEdge h0 = new HalfEdge(he.getV0(), v, he.getType());
        HalfEdge h1 = new HalfEdge(v, he.getV1(), he.getType());
        h0.connectTo(h1);
        Face f0 = he.getFace();
        h0.setFace(f0);
        h1.setFace(f0);
        if (EdgeType.isSettled(he.getType())) {
            he.getPrev().connectTo(h0);
            h1.connectTo(he.getNext());
        }
        ArrayList<HalfEdge> halfEdges0 = f0.getHalfEdges();
        int i = halfEdges0.indexOf(he);
        if (i == -1) {
            f0.addUnsettled(h0);
            f0.addUnsettled(h1);
            f0.removeUnsettled(he);
            return v;
        } else {
            halfEdges0.add(i, h0);
            halfEdges0.add(i + 1, h1);
            halfEdges0.remove(i + 2);
        }

        HalfEdge h0P = h0.getPair();
        HalfEdge h1P = h1.getPair();
        h1P.connectTo(h0P);

        HalfEdge heP = he.getPair();
        if (heP.getPrev() == null || heP.getNext() == null) {
            return v;
        }
        heP.getPrev().connectTo(h1P);
        h0P.connectTo(heP.getNext());
        Face f1 = heP.getFace();
        h0P.setFace(f1);
        h1P.setFace(f1);
        ArrayList<HalfEdge> halfEdges1 = f1.getHalfEdges();
        int j = halfEdges1.indexOf(heP);
        if (j == -1) {
        } else {
            halfEdges1.add(j, h1.getPair());
            halfEdges1.add(j + 1, h0.getPair());
            halfEdges1.remove(j + 2);
        }
        return v;
    }
}
