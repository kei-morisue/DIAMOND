/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.LinkedList;

import org.junit.Test;

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.action.Axiom1Action;
import diamond.controller.action.HalfEdgeFlipAction;
import diamond.controller.action.HalfEdgeSettleAction;
import diamond.controller.action.HalfEdgeUnfoldAction;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeUnfoldTest {
    private Context context = new Context();
    private PaintScreen paintScreen = new PaintScreen(context);
    private FoldedScreen foledScreen = new FoldedScreen(context);
    private Cp cp = context.getCp();
    private LinkedList<Face> faces = cp.getFaces();
    private static final double l = Config.PAPER_SIZE;

    public HalfEdgeUnfoldTest() {
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

    @Test
    public void Step0() {
        line0();
        context.setPaintAction(new HalfEdgeUnfoldAction());
        select0();
        TestUtil.validate(cp, .0, .0, EdgeType.CREASE);

        context.setPaintAction(new HalfEdgeUnfoldAction());
        select0();
        TestUtil.validate(cp, .0, .0, EdgeType.UNSETTLED_VALLEY);

        context.setPaintAction(new HalfEdgeFlipAction());
        select0();
        TestUtil.validate(cp, .0, .0, EdgeType.UNSETTLED_MOUNTAIN);

        context.setPaintAction(new HalfEdgeSettleAction());
        select0();
        TestUtil.validate(cp, .0, .0, EdgeType.MOUNTAIN);

        context.setPaintAction(new HalfEdgeUnfoldAction());
        select0();
        TestUtil.validate(cp, .0, .0, EdgeType.CREASE);

    }

}
