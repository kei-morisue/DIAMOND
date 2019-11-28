/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import diamond.Config;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class CrossPointUtil {
    private static double epsilon = Config.EPSILON;

    public static double[] getSplitter(
            Point2D.Double p0,
            Point2D.Double p1,
            Point2D.Double q0,
            Point2D.Double q1) {
        Point2D.Double d0 = Point2DUtil.minus(p1, p0);
        Point2D.Double d1 = Point2DUtil.minus(q1, q0);
        Point2D.Double diff = Point2DUtil.minus(q0, p0);
        double det = Point2DUtil.cross(d1, d0);
        if (det * det > epsilon * xxyy(d0) * xxyy(d1)) {
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

    private static double xxyy(Point2D.Double p) {
        return p.x * p.x + p.y * p.y;
    }

    public static double[] getSplitter(Point2D.Double v0,
            Point2D.Double v1,
            HalfEdge he) {
        return getSplitter(v0, v1, he.getV0(), he.getV1());
    }

    public static ArrayList<Vertex> getCrossPoints(Face face,
            Point2D.Double v0,
            Point2D.Double v1) {
        double[] ds = { 0.0, 0.0 };
        ArrayList<Vertex> crossPoints = new ArrayList<Vertex>();
        ArrayList<HalfEdge> halfEdges = new ArrayList<HalfEdge>();

        for (HalfEdge he : face.getHalfEdges()) {
            ds = getSplitter(v0, v1, he);
            if (ds == null) {
                continue;
            }
            if (Fuzzy.around(ds[1], 1.0)) {
                crossPoints.add(he.getV1());
            } else if (!Fuzzy.around(ds[1], 0.0)) {
                halfEdges.add(he);
            }
        }
        for (HalfEdge he : halfEdges) {
            ds = getSplitter(v0, v1, he);
            crossPoints.add(HalfEdgeSplitter.split(he, ds[1]));
        }
        return crossPoints;
    }
}
