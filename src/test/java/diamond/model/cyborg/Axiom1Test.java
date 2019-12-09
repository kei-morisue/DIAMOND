/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom2Action;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class Axiom1Test {
    private Context context = new Context();
    private PaintScreen paintScreen = new PaintScreen(context);
    private FoldedScreen foledScreen = new FoldedScreen(context);
    private Cp cp = context.getCp();
    private LinkedList<Face> faces = cp.getFaces();
    private static final double l = Config.PAPER_SIZE;

    public Axiom1Test() {
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);

    }

    private void line0() {
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line1() {
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);
    }

    private void line2() {
        TestUtil.click(-l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line34567() {
        context.setPaintAction(new Axiom2Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(l, l, context);
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(.0, -l, context);

        TestUtil.click(.0, l, context);
        TestUtil.click(-l, -l, context);

        TestUtil.click(.0, l, context);
        TestUtil.click(l, -l, context);

        TestUtil.click(.0, -l, context);
        TestUtil.click(l, l, context);

        TestUtil.click(-l * 0.5, .0, context);
        TestUtil.click(l * 0.5, .0, context);
    }

    @Test
    public void Step0() {
        line0();
        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 2);
        TestUtil.validate(cp, 4);
    }

    @Test
    public void Step1() {
        line0();
        line1();
        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 8);
        TestUtil.validate(cp, .0, .0, 4, false);
        TestUtil.validate(cp, 5);

    }

    @Test
    public void step2() {
        line2();
        TestUtil.validate(cp, 4);
        assertEquals(1, faces.size());
        TestUtil.validate(faces.get(0), 4, 0);
    }

    @Test
    public void step3() {
        line34567();
        TestUtil.validate(cp, 9);
        assertEquals(1, faces.size());
        TestUtil.validate(cp, -l * 0.5, .0, 5, false);
        TestUtil.validate(cp, l * 0.5, .0, 5, false);
        TestUtil.validate(cp, .0, .0, 4, false);
        TestUtil.validate(faces.get(0), 6, 24);
    }

}
