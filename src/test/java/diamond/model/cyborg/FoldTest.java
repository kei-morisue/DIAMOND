/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.awt.geom.Point2D.Double;
import java.util.LinkedList;

import org.junit.Test;

import diamond.Config;
import diamond.controller.action.Axiom1Action;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.model.cyborg.util.CenterPointUtil;

/**
 * @author Kei Morisue
 *
 */
public class FoldTest extends AbstractPaintActionTest {

    public FoldTest() {
        super();
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);

    }

    private void line0() {
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void select0() {
        TestUtil.click(.0, .0, context);
    }

    @Test
    public void step0() {
        line0();
        context.setPaintAction(new HalfEdgeSettleAction());
        select0();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        Face f0 = faces.get(0);
        Face f1 = faces.get(1);
        if (f0 == cp.getBaseFace()) {
            validate(f0, f1);
        } else {
            validate(f1, f0);
        }
    }

    private void validate(Face f0, Face f1) {
        for (HalfEdge he : f0.getHalfEdges()) {
            Vertex v0 = he.getV0();
            Double w0 = v0.getFoldedOffset();
            assertEquals(0.0, v0.distance(w0), Config.EPSILON);
        }
        for (HalfEdge he : f1.getHalfEdges()) {
            Vertex v0 = he.getV0();
            Double w0 = v0.getFoldedOffset();
            Double c = CenterPointUtil.get(v0, w0);
            assertEquals(c.x, c.y, Config.EPSILON);
        }
    }
}
