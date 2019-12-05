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
import diamond.controller.action.SymbolVertexPaintAction;
import diamond.model.symbol.Landmark;
import diamond.view.ui.screen.FoldedScreen;
import diamond.view.ui.screen.PaintScreen;

/**
 * @author Kei Morisue
 *
 */
public class SymbolTest {
    private Context context = new Context();
    private Cp cp = context.getCp();
    private PaintScreen paintScreen = new PaintScreen(context);
    private FoldedScreen foledScreen = new FoldedScreen(context);
    private LinkedList<Face> faces = cp.getFaces();
    private static final double l = Config.PAPER_SIZE;

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
