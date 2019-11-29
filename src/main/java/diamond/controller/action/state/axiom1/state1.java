/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom1;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.EdgeAdder;

/**
 * @author Kei Morisue
 *
 */
public class state1 extends VertexPickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = state0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = state1.class;
    }

    @Override
    protected void aftermath(Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        Vertex v0 = vertices.get(0);
        Vertex v1 = vertices.get(1);
        EdgeAdder.add(context, v0, v1);
        context.initialize();
    }

}
