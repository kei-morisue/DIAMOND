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
import diamond.controller.action.Axiom3Action;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class Axiom3Test {
    private Context context = new Context();
    private PaintScreen paintScreen = new PaintScreen(context);
    private FoldedScreen foledScreen = new FoldedScreen(context);
    private Cp cp = context.getCp();
    private LinkedList<Face> faces = cp.getFaces();
    private static final double l = Config.PAPER_SIZE;
    private static final double y = 3.0 - 2.0 * Math.sqrt(2);

    public Axiom3Test() {
        context.setPaintAction(new Axiom3Action());
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void line0() {
        TestUtil.click(l, l, context);
        TestUtil.click(-l, -l, context);
        TestUtil.click(l, -l, context);
        TestUtil.click(l, 0, context);
    }

    @Test
    public void Step0() {
        line0();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 5, 2);
        TestUtil.validate(cp, l, -l * y, 3, false);
    }

}
