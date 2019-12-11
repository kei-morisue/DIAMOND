/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.controller.action.Axiom3Action;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.controller.action.SymmetricAction;

/**
 * @author Kei Morisue
 *
 */
public class SymmetricTest extends AbstractPaintActionTest {
    private static final double y0 = 3.0 - 2.0 * Math.sqrt(2);
    private static final double y1 = 2.0 - Math.sqrt(2);

    public SymmetricTest() {
        super();
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        context.setPaintAction(new Axiom3Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
        TestUtil.click(l, -l, context);
        TestUtil.click(l, 0, context);
    }

    private void line1() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line2() {
        context.setPaintAction(new SymmetricAction());
        TestUtil.click(.0, -l * y1, context);
        TestUtil.click(.0, .0, context);
    }

    private void line3() {
        context.setPaintAction(new Axiom2Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line4() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line5() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, .0, context);
        TestUtil.click(l, -l, context);
    }

    private void line6() {
        context.setPaintAction(new SymmetricAction());
        TestUtil.click(-l * 0.75, -l * 0.75, context);
        TestUtil.click(-l * 0.5, -l * 0.25, context);
    }

    private void select0() {
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(-l * 0.75, .0, context);
        TestUtil.click(l * 0.75, .0, context);
    }

    @Test
    public void Step0() {
        line0();
        line1();
        line2();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(cp, 6);
        TestUtil.validate(cp, -l * y0, l, 3, false);
        TestUtil.validate(faces.get(0), 6, 6);
    }

    @Test
    public void Step1() {
        line3();
        line4();
        select0();
        line5();
        line6();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(2, faces.size());
        TestUtil.validate(cp, 9);
        TestUtil.validate(cp, .0, .0, 4, false);
    }

}
