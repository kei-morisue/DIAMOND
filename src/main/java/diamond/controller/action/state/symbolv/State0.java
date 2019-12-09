/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symbolv;

import java.util.HashMap;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Vertex;
import diamond.model.symbol.Symbol;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends VertexPickingState {
    private Class<? extends Symbol<Vertex>> symbolClass;

    public State0(Class<? extends Symbol<Vertex>> symbolClass) {
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
    protected void aftermath(Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        if (vertices.size() != 1) {
            context.initialize();
            return;
        }
        Vertex vertex = vertices.get(0);
        HashMap<Vertex, Symbol<Vertex>> symbols = context.getCp()
                .getSymbolsVertex();
        if (symbols.containsKey(vertex)) {
            symbols.get(vertex).flip(context.getCp());
        } else {
            try {
                Symbol<Vertex> symbol = symbolClass.newInstance();
                symbol.set(vertex);
                symbols.put(vertex, symbol);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        context.initialize();
    }

}
