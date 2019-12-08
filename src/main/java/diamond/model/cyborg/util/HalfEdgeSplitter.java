/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeSplitter {
    public static Vertex split(Point2D.Double p0, Point2D.Double p1,
            HalfEdge he) {
        double[] ds = CrossPointUtil.getSplitter(p0, p1, he);
        if (ds == null) {
            return null;
        }
        if (Fuzzy.around(ds[1], 1.0)) {
            return he.getV1();
        }
        if (Fuzzy.around(ds[1], 0.0)) {
            return he.getV0();
        }
        return split(he, ds[1]).getV0();
    }

    public static HalfEdge split(HalfEdge he, double t) {
        Double p = Point2DUtil.split(he.getV0(), he.getV1(), t);
        Vertex v = new Vertex(p);
        return split(he, v);
    }

    private static HalfEdge split(HalfEdge he, Vertex v) {
        Vertex v0 = he.getV0();
        if (v0 == v) {
            return he;
        }
        Vertex v1 = he.getV1();
        if (v1 == v) {
            return null;
        }
        v1.remove(he);
        v0.remove(he);
        HalfEdge h0 = new HalfEdge(v0, v, he.getType());
        HalfEdge h1 = new HalfEdge(v, v1, he.getType());
        Face f = he.getFace();
        HalfEdge heP = he.getPair();
        HalfEdge h0P = h0.getPair();
        HalfEdge h1P = h1.getPair();
        manageFace(he, h0, h1, f);
        manageFace(heP, h0P, h1P, heP.getFace());
        link(he, h0, h1);
        link(heP, h1P, h0P);
        return h1;
    }

    private static void link(HalfEdge he, HalfEdge h0, HalfEdge h1) {
        h0.connectTo(h1);
        if (he.getPrev() != null && he.getNext() != null) {
            he.getPrev().connectTo(h0);
            h1.connectTo(he.getNext());
        }
    }

    private static void manageFace(HalfEdge he, HalfEdge h0, HalfEdge h1,
            Face f) {
        if (f == null) {
            return;
        }
        f.add(h0);
        f.add(h1);
        f.remove(he);
    }
}
