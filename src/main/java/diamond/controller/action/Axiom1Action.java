/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.axiom1.State0;
import diamond.view.ui.screen.draw.PaintScreenDrawer;

/**
 * @author Kei Morisue
 *
 */
public class Axiom1Action extends AbstractPaintAction {

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenDrawer.drawTemporaryLine(g2d, context);
    }

    @Override
    protected void setInitialState() {
        setActionState(new State0());
    }

}
