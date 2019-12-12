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
import diamond.controller.action.Axiom4Action;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.controller.action.SymmetricAction;

/**
 * @author Kei Morisue
 *
 */
public class SymmetricTest2 extends AbstractPaintActionTest {

    public SymmetricTest2() {
        super();
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(-l * 0.5, -l * 0.5, context);
    }

    private void line1() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(-l * 0.5, l * 0.5, context);
        TestUtil.click(l * 0.5, -l * 0.5, context);
    }

    private void line2() {
        context.setInputType(EdgeType.UNSETTLED_MOUNTAIN);
        context.setPaintAction(new Axiom2Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, -l, context);
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(-l * 0.5, 0, context);
        TestUtil.click(l * 0.5, 0, context);
    }

    private void line3() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom4Action());
        TestUtil.click(l, 0, context);
        TestUtil.click(l * 0.5, -l * 0.5, context);
    }

    private void line4() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom4Action());
        TestUtil.click(l * 0.5, -l * 0.5, context);
        TestUtil.click(l * 0.5, 0, context);
    }

    private void line5() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new SymmetricAction());
        TestUtil.click(l * 0.5, -0.25 * l, context);
        TestUtil.click(l * 0.25, .0, context);
    }

    @Test
    public void Step0() {
        line0();
        line1();
        line2();
        line3();
        line4();
        line5();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        assertEquals(6, faces.size());
        TestUtil.validate(cp, 10);
        TestUtil.validate(cp, l * 0.5, l * 0.25, EdgeType.UNSETTLED_VALLEY);
        TestUtil.validate(cp, l * 0.5, l * 0.5, 3, false);
    }

}
