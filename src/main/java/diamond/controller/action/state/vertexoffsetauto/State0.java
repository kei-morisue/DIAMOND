/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexoffsetauto;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.VertexOffsetter;

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
    protected void undo(Context context) {
        Vertex vertex = context.getPointer().getVertex();
        if (vertex == null) {
            context.initialize();
            return;
        }
        VertexOffsetter.reset(context.getCp(), vertex);
        context.initialize();
    }

    @Override
    protected void aftermath(Context context) {
        Vertex vertex = getVertex(context);
        if (vertex == null) {
            context.initialize();
            return;
        }
        VertexOffsetter.offset(context.getCp(), vertex);
        context.initialize();
    }

    private Vertex getVertex(Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        if (vertices.size() != 1) {
            return null;
        }
        Vertex vertex = vertices.get(0);
        return vertex;
    }

}
