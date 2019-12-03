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
        ArrayList<HalfEdge> halfEdges = face.getHalfEdges();
        assertEquals(edgeNum, halfEdges.size());
        for (HalfEdge he : halfEdges) {
            assertEquals(face, he.getFace());
            assertEquals(he, he.getNext().getPrev());
        }
        HashSet<HalfEdge> unsettledLines = face.getUnsettledLines();
        assertEquals(unsettledNum, unsettledLines.size());
    }
}
