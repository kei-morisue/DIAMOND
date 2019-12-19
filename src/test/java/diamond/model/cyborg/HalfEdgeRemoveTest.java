/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.LinkedList;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.controller.action.HalfEdgeRemoveAction;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeRemoveTest extends AbstractPaintActionTest {

    public HalfEdgeRemoveTest() {
        super();
        context.setInputType(EdgeType.UNSETTLED_VALLEY);

    }

    private void line0() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, -l, context);
        TestUtil.click(l, l, context);
    }

    private void select0() {
        context.setPaintAction(new HalfEdgeRemoveAction());
        TestUtil.click(.0, .0, context);
    }

    private void line1() {
        context.setPaintAction(new Axiom2Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(l, l, context);
    }

    private void line2() {
        context.setInputType(EdgeType.CREASE);
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line3() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);
    }

    private void select1() {
        context.setPaintAction(new HalfEdgeRemoveAction());
        TestUtil.click(.0, .0, context);
    }

    private void select2() {
        context.setPaintAction(new HalfEdgeRemoveAction());
        TestUtil.click(-0.5 * l, 0.5 * l, context);
    }

    private void select3() {
        context.setPaintAction(new HalfEdgeRemoveAction());
        TestUtil.click(0.5 * l, -0.5 * l, context);
    }

    @Test
    public void Step0() {
        line0();
        select0();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();

        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 4, 0);
    }

    @Test
    public void Step1() {
        line2();
        line3();
        select2();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 4, 6);
        select3();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 4, 2);
        select3();
    }

}
