/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;

import diamond.controller.Context;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public class TestUtil {
    public static void click(double x, double y, Context context) {
        context.setMousePoint(new Point2D.Double(x, y));
        context.getPaintAction().onMove(context);
        context.getPaintAction().doAction(context);
    }

    static void validate(Face face, int edgeNum, int unsettledNum) {
        ArrayList<HalfEdge> halfEdges = face.getSortedEdges();
        assertEquals(edgeNum, halfEdges.size());
        for (HalfEdge he : halfEdges) {
            assertEquals(face, he.getFace());
            assertEquals(he, he.getNext().getPrev());
        }
        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
        assertEquals(unsettledNum, unsettledLines.size());
        for (HalfEdge he : unsettledLines) {
            assertEquals(he.getFace(), face);
        }
    }

    static void validate(Cp cp, int vNum) {
        assertEquals(vNum, cp.getVertices().size());
    }

    static void validate(Cp cp, double x, double y, int heNum,
            boolean isWrong) {
        Point2D.Double p = new Point2D.Double(x, y);
        for (Vertex v : cp.getVertices()) {
            if (Fuzzy.around(v.distance(p), 0.0)) {
                assertEquals(heNum, v.getHalfEdges().size());
                assertTrue(v.getProperty().isWrong == isWrong);
                return;
            }
        }
        assertTrue(false);
    }

    static void validate(Cp cp, double x, double y, EdgeType type) {
        Point2D.Double p = new Point2D.Double(x, y);
        for (HalfEdge he : cp.getHalfEdges()) {
            if (Fuzzy.around(
                    Point2DUtil.distanceToSegment(p, he.getV0(), he.getV1()),
                    0.0)) {
                assertTrue(he.getType() == type);
                return;
            }
        }
        assertTrue(false);
    }

}
