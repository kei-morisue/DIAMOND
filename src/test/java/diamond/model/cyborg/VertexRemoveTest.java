/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import org.junit.Test;

import diamond.controller.action.VertexAddAction;
import diamond.controller.action.VertexRemoveAction;

/**
 * @author Kei Morisue
 *
 */
public class VertexRemoveTest extends AbstractPaintActionTest {
    private static final double s = 42.0;

    public VertexRemoveTest() {
        super();
        context.setInputType(EdgeType.UNSETTLED_VALLEY);
    }

    private void v0() {
        context.setPaintAction(new VertexAddAction());
        TestUtil.click(s, l, context);
    }

    private void select0() {
        context.setPaintAction(new VertexRemoveAction());
        TestUtil.click(s, l, context);
    }

    private void v1() {
        context.setPaintAction(new VertexAddAction());
        TestUtil.click(-l, s, context);
    }

    private void select1() {
        context.setPaintAction(new VertexRemoveAction());
        TestUtil.click(-l, s, context);
    }

    @Test
    public void Step0() {
        v0();
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 5, 0);
        TestUtil.validate(cp, 42.0, l, 2, false);

        select0();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 4, 0);

    }

    @Test
    public void Step1() {
        v1();
        TestUtil.validate(cp, 5);
        TestUtil.validate(faces.get(0), 5, 0);
        TestUtil.validate(cp, -l, s, 2, false);

        select1();
        TestUtil.validate(cp, 4);
        TestUtil.validate(faces.get(0), 4, 0);

    }

}
