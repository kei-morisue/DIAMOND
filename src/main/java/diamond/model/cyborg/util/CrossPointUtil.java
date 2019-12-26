/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.Config;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;
import diamond.model.math.NormComparator;

/**
 * @author Kei Morisue
 *
 */
public class CrossPointUtil {
    private final static double epsilon = Config.EPSILON;

    public static double[] getSplitter(
            Point2D.Double p0,
            Point2D.Double p1,
            Point2D.Double q0,
            Point2D.Double q1) {
        Point2D.Double d0 = Point2DUtil.sub(p1, p0);
        Point2D.Double d1 = Point2DUtil.sub(q1, q0);
        Point2D.Double diff = Point2DUtil.sub(q0, p0);
        double det = Point2DUtil.cross(d1, d0);
        if (!isDet0(p0, p1, q0, q1)) {
            double invDet = 1.0 / det;
            double s = Point2DUtil.cross(d1, diff) * invDet;
            if (!Fuzzy.between(s, 0.0, 1.0)) {
                return null;
            }
            double t = Point2DUtil.cross(d0, diff) * invDet;
            if (!Fuzzy.between(t, 0.0, 1.0)) {
                return null;
            }
            double[] ds = { s, t };
            return ds;
        }
        return null;
    }

    public static boolean isDet0(Point2D.Double p0,
            Point2D.Double p1,
            Point2D.Double q0,
            Point2D.Double q1) {
        Point2D.Double d0 = Point2DUtil.sub(p1, p0);
        Point2D.Double d1 = Point2DUtil.sub(q1, q0);
        double det = Point2DUtil.cross(d1, d0);
        return det * det <= epsilon * xxyy(d0) * xxyy(d1);
    }

    private static double xxyy(Point2D.Double p) {
        return p.x * p.x + p.y * p.y;
    }

    public static double[] getSplitter(Point2D.Double v0,
            Point2D.Double v1,
            HalfEdge he) {
        return getSplitter(v0, v1, he.getV0(), he.getV1());
    }

    public static ArrayList<HalfEdge> splitOutLines(Face face,
            Point2D.Double p0,
            Point2D.Double p1) {
        double[] ds = { 0.0, 0.0 };
        ArrayList<HalfEdge> halfEdges = new ArrayList<HalfEdge>();

        ArrayList<HalfEdge> copy = new ArrayList<HalfEdge>(face.getSortedEdges());
        for (HalfEdge he : copy) {
            if (isDet0(p0, p1, he.getV0(), he.getV1())
                    && isDet0(p0, he.getV0(), p1, he.getV1())
                    && isDet0(p0, he.getV1(), p1, he.getV0())) {
                halfEdges.clear();
                return halfEdges;
            }
            ds = getSplitter(p0, p1, he);
            if (ds == null) {
                continue;
            }
            if (Fuzzy.around(ds[1], 0.0)) {
                halfEdges.add(he);
            } else if (!Fuzzy.around(ds[1], 1.0)) {
                HalfEdge he1 = HalfEdgeSplitter.split(he, ds[1]);
                if (he1 != null) {
                    halfEdges.add(he1);
                }
            }
        }
        return halfEdges;
    }

    public static ArrayList<Vertex> splitUnsettledLines(Face face,
            Point2D.Double p0, Point2D.Double p1) {
        HashSet<Vertex> crossPoints = new HashSet<Vertex>();
        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
        HashSet<HalfEdge> copy = new HashSet<HalfEdge>();
        copy.addAll(unsettledLines);
        for (HalfEdge he : copy) {
            if (he.getProperty().isDisabled()) {
                continue;
            }
            Vertex v = HalfEdgeSplitter.split(p0, p1, he);
            if (v != null) {
                crossPoints.add(v);
            }
        }
        ArrayList<Vertex> ordered = new ArrayList<Vertex>();
        ordered.addAll(crossPoints);
        ordered.sort(new NormComparator(p0));
        return ordered;
    }

}
