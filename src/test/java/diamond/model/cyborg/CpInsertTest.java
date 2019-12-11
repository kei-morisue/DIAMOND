/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.LinkedList;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.controller.action.HalfEdgeUnfoldAction;
import diamond.controller.action.SymbolHalfEdgePaintAction;
import diamond.model.symbol.arrow.ArrowFoldUnfold;

/**
 * @author Kei Morisue
 *
 */
public class CpInsertTest extends AbstractPaintActionTest {
    private static final double s = 42.0;

    public CpInsertTest() {
        super();
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void select0() {
        context.setPaintAction(new HalfEdgeSettleAction());
        TestUtil.click(.0, .0, context);
    }

    private void select1() {
        context.setPaintAction(new HalfEdgeUnfoldAction());
        TestUtil.click(.0, .0, context);
    }

    private void select2() {
        context.setPaintAction(
                new SymbolHalfEdgePaintAction(ArrowFoldUnfold.class));
        TestUtil.click(.0, .0, context);
    }

    @Test
    public void Step0() {
        line0();
        context.insertCp();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 3, 0);
        TestUtil.validate(faces.get(1), 3, 0);
        TestUtil.validate(cp, -l, -l, 3, false);
        TestUtil.validate(cp, l, l, 3, false);
    }

    @Test
    public void Step1() {
        line0();
        select0();
        context.insertCp();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 3, 0);
        TestUtil.validate(faces.get(1), 3, 0);
        TestUtil.validate(cp, -l, -l, 3, false);
        TestUtil.validate(cp, l, l, 3, false);
    }

    @Test
    public void Step2() {
        line0();
        select1();
        context.insertCp();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(cp, .0, .0, EdgeType.CREASE);
    }

    @Test
    public void Step3() {
        line0();
        select2();
        context.insertCp();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(cp, .0, .0, EdgeType.CREASE);
    }

}
