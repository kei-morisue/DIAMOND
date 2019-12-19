/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import diamond.controller.action.Axiom2Action;

/**
 * @author Kei Morisue
 *
 */
public class Axiom2Test extends AbstractPaintActionTest {

    public Axiom2Test() {
        super();
        context.setPaintAction(new Axiom2Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line1() {
        TestUtil.click(-l, l, context);
        TestUtil.click(l, l, context);
    }

    private void line2() {
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, 0.0, context);
    }

    private void line3() {
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);
    }

    @Test
    public void Step0() {
        line0();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 6, 2);
        TestUtil.validate(cp, 6);
    }

    @Test
    public void Step1() {
        line0();
        line1();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 8, 8);
        TestUtil.validate(cp, 9);
    }

    @Test
    public void step2() {
        line0();
        line2();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 8, 4);
        TestUtil.validate(cp, 8);
    }

    @Test
    public void step3() {
        line3();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(cp, 4);
    }

}
