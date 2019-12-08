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
        for (Face face : cp.getFaces()) {
            ArrayList<HalfEdge> externals = CrossPointUtil.splitOutLines(face,
                    p0, p1);
            ArrayList<Vertex> internals = CrossPointUtil
                    .splitUnsettledLines(face, p0, p1);
            buildHalfEdges(externals, internals, face, type);
        }
    }

    private static void buildHalfEdges(ArrayList<HalfEdge> externals,
            ArrayList<Vertex> internals, Face face, EdgeType type) {
        if (externals.size() == 0) {
            return;
        }
        if (externals.size() == 1) {
            internals.add(0, externals.get(0).getV0());
        } else if (externals.size() == 2) {
            internals.add(0, externals.get(0).getV0());
            internals.add(externals.get(1).getV0());
        }
        HalfEdgeConnector.connect(face, internals, type);
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
