/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.util.LinkedList;

import diamond.Config;
import diamond.controller.Context;
import diamond.view.ui.panel.East;
import diamond.view.ui.screen.FoldedScreen;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPaintActionTest {
    protected Context context = new Context();
    protected Cp cp = context.getCp();
    protected LinkedList<Face> faces = cp.getFaces();
    protected static final double l = Config.PAPER_SIZE;

    protected AbstractPaintActionTest() {
        new East(context);
        new FoldedScreen(context);
    }
}
