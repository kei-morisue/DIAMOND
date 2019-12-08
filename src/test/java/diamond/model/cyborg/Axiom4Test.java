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
import diamond.controller.action.Axiom4Action;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class Axiom4Test {
    private Context context = new Context();
    private PaintScreen paintScreen = new PaintScreen(context);
    private FoldedScreen foledScreen = new FoldedScreen(context);
    private Cp cp = context.getCp();
    private LinkedList<Face> faces = cp.getFaces();
    private static final double l = Config.PAPER_SIZE;

    public Axiom4Test() {
        context.setPaintAction(new Axiom1Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
    }

    private void line1() {
        TestUtil.click(-l, l, context);
        TestUtil.click(0, 0, context);
    }

    private void line01() {
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);

    }

    private void line2() {
        TestUtil.click(.0, .0, context);
        TestUtil.click(.0, l, context);
    }

    private void line3() {
        TestUtil.click(.0, .0, context);
        TestUtil.click(-l, .0, context);
    }

    private void line4() {
        TestUtil.click(.0, .0, context);
        TestUtil.click(0, -l, context);
    }

    private void line5() {
        TestUtil.click(.0, .0, context);
        TestUtil.click(l, .0, context);
    }

    @Test
    public void Step0() {
        line0();
        context.setPaintAction(new Axiom4Action());
        line1();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(cp, 0, 0, 3, false);
        TestUtil.validate(faces.get(0), 4, 6);
    }

    @Test
    public void Step1() {
        line01();
        context.setPaintAction(new Axiom4Action());
        line2();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 6);
        TestUtil.validate(cp, .0, .0, 5, false);
        TestUtil.validate(faces.get(0), 5, 10);

        line3();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 7);
        TestUtil.validate(cp, .0, .0, 6, false);
        TestUtil.validate(faces.get(0), 6, 12);

        line4();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 8);
        TestUtil.validate(cp, .0, .0, 7, false);
        TestUtil.validate(faces.get(0), 7, 14);

        line5();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 9);
        TestUtil.validate(cp, .0, .0, 8, false);
        TestUtil.validate(faces.get(0), 8, 16);
    }

}
