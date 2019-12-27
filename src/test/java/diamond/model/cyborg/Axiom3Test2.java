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

/**
 * @author Kei Morisue
 *
 */
public class Axiom3Test2 extends AbstractPaintActionTest {

    public Axiom3Test2() {
        super();
    }

    private void line0() {
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.CREASE);
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line1() {
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.CREASE);
        TestUtil.click(l, -l, context);
        TestUtil.click(-l, l, context);
    }

    private void line2() {
        context.setPaintAction(new Axiom2Action());
        context.setInputType(EdgeType.CREASE);
        TestUtil.click(l, l, context);
        TestUtil.click(l, -l, context);
    }

    private void line3() {
        context.setPaintAction(new Axiom2Action());
        context.setInputType(EdgeType.CREASE);
        TestUtil.click(l, l, context);
        TestUtil.click(-l, l, context);
    }

    private void line4() {
        context.setPaintAction(new Axiom3Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        TestUtil.click(.0, .0, context);
        TestUtil.click(l, l, context);
        TestUtil.click(l, -l, context);
        TestUtil.click(0.5 * l, -l, context);
    }

    private void line5() {
        context.setPaintAction(new Axiom3Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        TestUtil.click(.0, .0, context);
        TestUtil.click(-l, -l, context);
        TestUtil.click(l, -l, context);
        TestUtil.click(l, -0.5 * l, context);
    }

    private void line6() {
        context.setPaintAction(new Axiom3Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        TestUtil.click(.0, .0, context);
        TestUtil.click(l, l, context);
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, 0.5 * l, context);
    }

    private void line7() {
        context.setPaintAction(new Axiom3Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        TestUtil.click(.0, .0, context);
        TestUtil.click(-l, -l, context);
        TestUtil.click(-l, l, context);
        TestUtil.click(-0.5 * l, l, context);
    }

    @Test
    public void Step0() {
        line0();
        line1();
        line2();
        line3();
        line4();
        line5();
        line6();
        line7();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 12, 52);
        TestUtil.validate(cp, 19);
    }

}
