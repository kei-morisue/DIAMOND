/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexremove;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.VertexRemover;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends VertexPickingState {

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
        Stack<Vertex> vs = context.getPicker().getVertices();
        if (vs.size() != 1) {
            context.initialize();
            return;
        }
        Vertex v = vs.get(0);
        VertexRemover.remove(context, v);
        context.fold();
        context.initialize();
    }

}
