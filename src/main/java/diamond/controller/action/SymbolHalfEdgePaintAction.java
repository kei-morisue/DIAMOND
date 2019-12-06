/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.symbolhe.State0;
import diamond.model.cyborg.HalfEdge;
import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class SymbolHalfEdgePaintAction extends AbstractPaintAction {
    public SymbolHalfEdgePaintAction(
            Class<? extends Symbol<HalfEdge>> symbolClass) {
        setActionState(new State0(symbolClass));
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
    }

    @Override
    protected void setInitialState() {
    }

}
