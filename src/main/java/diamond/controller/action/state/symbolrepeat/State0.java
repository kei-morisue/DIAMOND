/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symbolrepeat;

import java.util.HashMap;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.symbol.Symbol;
import diamond.view.ui.panel.SymbolRepeatDialog;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends HalfEdgePickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = State0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State0.class;
    }

    @Override
    protected void undo(Context context) {
        HalfEdge halfEdge = context.getPointer().getHalfEdge();
        HashMap<HalfEdge, Symbol<HalfEdge>> symbols = context.getCp()
                .getSymbolsHalfEdge();
        if (symbols.containsKey(halfEdge)) {
            symbols.remove(halfEdge);
        }
    }

    @Override
    protected boolean act(Context context) {
        if (!super.act(context)) {
            return false;
        }
        HalfEdge he = context.getPicker().getHalfEdges().get(0);
        Symbol<HalfEdge> symbol = context.getCp().getSymbolsHalfEdge().get(he);
        if (symbol != null) {
            symbol.flip(context.getCp());
        } else {
            SymbolRepeatDialog dialog = new SymbolRepeatDialog(context);
            dialog.showDialog();
        }
        return true;
    }

    @Override
    protected void aftermath(Context context) {

        context.initialize();
    }

}
