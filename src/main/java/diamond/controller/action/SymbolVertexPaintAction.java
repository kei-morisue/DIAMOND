/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.symbolv.State0;
import diamond.model.cyborg.Vertex;
import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class SymbolVertexPaintAction extends AbstractPaintAction {
    public SymbolVertexPaintAction(
            Class<? extends Symbol<Vertex>> symbolClass) {
        setActionState(new State0(symbolClass));
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
    }

    @Override
    protected void setInitialState() {
    }

}
