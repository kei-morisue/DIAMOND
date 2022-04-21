/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import diamond.controller.action.Axiom1Action;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeUnfoldTest extends AbstractPaintActionTest {

    public HalfEdgeUnfoldTest() {
        super();
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, -l, context);
        TestUtil.click(l, l, context);
    }

    private void select0() {
        TestUtil.click(.0, .0, context);
    }

    //    @Test
    //    public void Step0() {
    //        line0();
    //        context.setPaintAction(new HalfEdgeUnfoldAction());
    //        select0();
    //        Cp cp = context.getCp();
    //        LinkedList<Face> faces = cp.getFaces();
    //
    //        TestUtil.validate(cp, .0, .0, EdgeType.CREASE);
    //
    //        context.setPaintAction(new HalfEdgeUnfoldAction());
    //        select0();
    //        TestUtil.validate(cp, .0, .0, EdgeType.UNSETTLED_VALLEY);
    //
    //        context.setPaintAction(new HalfEdgeFlipAction());
    //        select0();
    //        TestUtil.validate(cp, .0, .0, EdgeType.UNSETTLED_MOUNTAIN);
    //
    //        context.setPaintAction(new HalfEdgeSettleAction());
    //        select0();
    //        TestUtil.validate(cp, .0, .0, EdgeType.MOUNTAIN);
    //
    //        context.setPaintAction(new HalfEdgeUnfoldAction());
    //        select0();
    //        TestUtil.validate(cp, .0, .0, EdgeType.CREASE);
    //
    //    }

}
