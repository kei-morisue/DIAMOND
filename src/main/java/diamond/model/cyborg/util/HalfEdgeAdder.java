/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import diamond.controller.Context;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeAdder {
    public static void add(Context context, Point2D.Double p0,
            Point2D.Double p1) {
        Cp cp = context.getCp();
        EdgeType type = context.getInputType();
        ArrayList<Face> toBeDeleted = new ArrayList<Face>();
        ArrayList<Face> toBeAdded = new ArrayList<Face>();
        for (Face face : cp.getFaces()) {
            ArrayList<Vertex> crossPoints = CrossPointUtil.getCrossPoints(face,
                    p0, p1);
            if (crossPoints.size() == 1) {
                Vertex c0 = crossPoints.get(0);
                HalfEdge he = new HalfEdge(c0, inside(face, p0, p1), type);
                FaceCutter.cut(face, he);
            } else if (crossPoints.size() == 2) {
                Vertex c0 = crossPoints.get(0);
                Vertex c1 = crossPoints.get(1);
                HalfEdge he = new HalfEdge(c0, c1, type);
                Face[] faces = FaceSplitter.split(face, he);
                toBeAdded.add(faces[0]);
                toBeDeleted.add(face);
                if (EdgeType.isSettled(type)) {
                    toBeAdded.add(faces[1]);
                }
            }
        }
        for (Face face : toBeDeleted) {
            cp.remove(face);
        }
        for (Face face : toBeAdded) {
            cp.add(face);
        }
    }

    public static Vertex inside(Face face, Point2D.Double p0,
            Point2D.Double p1) {
        if (FaceUtil.onFace(face, p0)) {
            return find(face, p0);
        }
        if ((FaceUtil.onFace(face, p1))) {
            return find(face, p1);
        }
        return null;
    }

    private static Vertex find(Face face, Point2D.Double p) {
        for (HalfEdge halfEdge : face.getUnsettledLines()) {
            Vertex v0 = halfEdge.getV0();
            Vertex v1 = halfEdge.getV1();
            if (Fuzzy.around(v0.distance(p), 0.0)) {
                return v0;
            }
            if (Fuzzy.around(v1.distance(p), 0.0)) {
                return v1;
            }
        }
        return new Vertex(p);
    }
}
