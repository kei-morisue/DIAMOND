/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import diamond.controller.action.Axiom1Action;
import diamond.controller.action.Axiom4Action;
import diamond.controller.action.SymmetricAction;

/**
 * @author Kei Morisue
 *
 */
public class SymmetricTest3 extends AbstractPaintActionTest {

    public SymmetricTest3() {
        super();
    }

    private void line0() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom1Action());
        TestUtil.click(-l, l, context);
        TestUtil.click(l, -l, context);
    }

    private void line1() {
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
        context.setPaintAction(new Axiom4Action());
        TestUtil.click(-l, -l, context);
        TestUtil.click(.0, .0, context);
    }

    private void line2() {
        context.setInputType(EdgeType.UNSETTLED_MOUNTAIN);
        context.setPaintAction(new SymmetricAction());
        TestUtil.click(-l * 0.5, -l * 0.5, context);
        TestUtil.click(-l * 0.5, l * 0.5, context);
    }

    @Test
    public void Step0() {
        line0();
        line1();
        line2();
        Cp cp = context.getCp();
        LinkedList<Face> faces = cp.getFaces();
        assertEquals(1, faces.size());
        TestUtil.validate(cp, .0, .0, 4, false);
        TestUtil.validate(cp, 5);
    }

}
