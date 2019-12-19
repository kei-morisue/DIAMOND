/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.controller.action.FaceBaseAction;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.model.cyborg.util.FaceUtil;

/**
 * @author Kei Morisue
 *
 */
public class FoldTest2 extends AbstractPaintActionTest {
    private static final double s = l * (Math.sqrt(2.0) - 1.0);

    public FoldTest2() {
        super();
    }

    private void line0() {
        context.setPaintAction(new Axiom2Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, -l, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(.0, .0, context);
    }

    private void line1() {
        context.setPaintAction(new Axiom2Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        TestUtil.click(-l, l, context);
        TestUtil.click(l, l, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(.0, 0.5 * l, context);
        TestUtil.click(.0, -0.5 * l, context);

    }

    private void line2() {
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.UNSETTLED_MOUNTAIN);
        TestUtil.click(-l, l, context);
        TestUtil.click(.0, .0, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(-0.5 * l, 0.5 * l, context);
    }

    private void line3() {
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.UNSETTLED_MOUNTAIN);
        TestUtil.click(.0, .0, context);
        TestUtil.click(l, -l, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(0.5 * l, -0.5 * l, context);
    }

    private void select0() {
        context.setPaintAction(new FaceBaseAction());
        TestUtil.click(-0.5 * l, -0.5 * l, context);
    }

    @Test
    public void step0() {
        line0();
        assertEquals(2, context.getCp().getFaces().size());
        line1();
        assertEquals(4, context.getCp().getFaces().size());
        line2();
        assertEquals(5, context.getCp().getFaces().size());
        line3();
        assertEquals(6, context.getCp().getFaces().size());
        TestUtil.validate(context.getCp(), 9);
        TestUtil.validate(context.getCp(), .0, .0, 6, false);
        select0();
        Face f0 = getFace(-0.5 * l, -0.5 * l);
        Face f01 = getFace(0.5 * s, -0.5 * l);
        Face f02 = getFace(-l * 0.5, 0.5 * s);

        Face f11 = getFace(0.5 * l, -0.5 * s);
        Face f12 = getFace(-s * 0.5, 0.5 * l);

        Face f1 = getFace(l * 0.5, 0.5 * l);

        validate(f0, f01);
        validate(f0, f02);

        validate(f01, f11);
        validate(f02, f12);

        validate(f11, f1);
        validate(f12, f1);

    }

    private void validate(Face f0, Face f1) {
        Cp cp = context.getCp();
        int i0 = cp.getFaces().indexOf(f0);
        int i1 = cp.getFaces().indexOf(f1);
        assertTrue(i0 < i1);
    }

    private Face getFace(double x, double y) {
        Cp cp = context.getCp();
        for (Face face : cp.getFaces()) {
            if (FaceUtil.onFace(face, new Point2D.Double(x, y))) {
                return face;
            }
        }
        return null;
    }
}
