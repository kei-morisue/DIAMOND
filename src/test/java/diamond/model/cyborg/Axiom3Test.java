/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.controller.action.Axiom3Action;
import diamond.controller.action.HalfEdgeSettleAction;

/**
 * @author Kei Morisue
 *
 */
public class Axiom3Test extends AbstractPaintActionTest {
    private static final double y = 3.0 - 2.0 * Math.sqrt(2);
    private static final double x = 2.0 - Math.sqrt(2);

    public Axiom3Test() {
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
        context.setPaintAction(new Axiom2Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void select0() {
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(.0, .0, context);
    }

    private void line2() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line3() {
        context.setPaintAction(new Axiom3Action());
        TestUtil.click(-l, .0, context);
        TestUtil.click(-l, -l, context);
        TestUtil.click(.0, .0, context);
        TestUtil.click(-l * 0.5, 0, context);
    }

    private void line4() {
        context.setPaintAction(new Axiom3Action());
        TestUtil.click(.0, .0, context);
        TestUtil.click(l, l, context);
        TestUtil.click(l, .0, context);
        TestUtil.click(l * 0.5, .0, context);
    }

    @Test
    public void Step0() {
        line0();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 5, 2);
        TestUtil.validate(cp, l, -l * y, 3, false);
    }

    @Test
    public void Step1() {
        line1();
        select0();
        line2();
        line3();
        line4();
        assertEquals(2, faces.size());
        TestUtil.validate(cp, 9);
        TestUtil.validate(cp, l * x, .0, 3, false);
        TestUtil.validate(cp, -l * x, .0, 3, false);
        TestUtil.validate(cp, .0, .0, 4, false);
        TestUtil.validate(faces.get(0), 7, 4);
        TestUtil.validate(faces.get(1), 7, 4);
    }

}
