/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symbolhe;

import java.util.HashMap;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends HalfEdgePickingState {
    private Class<? extends Symbol<HalfEdge>> symbolClass;

    public State0(Class<? extends Symbol<HalfEdge>> symbolClass) {
        this.symbolClass = symbolClass;
    }

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
    protected void aftermath(Context context) {
        HalfEdge halfEdge = context.getPicker().getHalfEdges().get(0);
        HashMap<HalfEdge, Symbol<HalfEdge>> symbols = context.getCp()
                .getSymbolsHalfEdge();
        if (symbols.containsKey(halfEdge)) {
            symbols.get(halfEdge).flip(context.getCp());
        } else {
            try {
                Symbol<HalfEdge> symbol = symbolClass.newInstance();
                symbol.set(halfEdge);
                symbols.put(halfEdge, symbol);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        context.initialize();
    }
}
