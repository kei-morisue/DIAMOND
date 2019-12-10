/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import static org.junit.Assert.*;

import org.junit.Test;

import diamond.controller.action.SymbolVertexPaintAction;
import diamond.model.symbol.Landmark;

/**
 * @author Kei Morisue
 *
 */
public class SymbolTest extends AbstractPaintActionTest {

    public SymbolTest() {
        super();
    }

    private void v0() {
        TestUtil.click(l, l, context);
    }

    @Test
    public void landmark() {
        context.setPaintAction(new SymbolVertexPaintAction(Landmark.class));
        v0();
        assertEquals(1, cp.getSymbolsVertex().size());
        v0();
        assertEquals(0, cp.getSymbolsVertex().size());
    }

}
