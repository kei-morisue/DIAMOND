/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symbolhescale;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.OffsetUtil;
import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends AbstractState {

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
        getSymbol(context, getHalfEdge(context))
                .setScale(1.0);
        context.getPicker().popVertex();
        context.setPaintScreen("paint");
        context.initialize();
    }

    @Override
    protected void aftermath(Context context) {
        context.setPaintScreen("paint");
        context.initialize();
    }

    @Override
    protected boolean act(Context context) {
        return true;
    }

    @Override
    public void setPointer(Context context) {
        HalfEdge halfEdge = getHalfEdge(context);
        OffsetUtil.setScale(context, getSymbol(context, halfEdge));
    }

    private Symbol<HalfEdge> getSymbol(Context context, HalfEdge halfEdge) {
        return context.getCp().getSymbolsHalfEdge()
                .get(halfEdge);
    }

    private HalfEdge getHalfEdge(Context context) {
        Stack<HalfEdge> hes = context.getPicker().getHalfEdges();
        if (hes.size() != 1) {
            undo(context);
            return null;
        }
        return hes.get(0);
    }

}
