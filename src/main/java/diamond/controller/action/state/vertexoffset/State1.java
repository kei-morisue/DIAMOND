/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexoffset;

import java.awt.geom.Point2D;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.OffsetUtil;

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
        getVertex(context).setOffset(new Point2D.Double());
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
        Vertex vertex = getVertex(context);
        OffsetUtil.setOffset(context, vertex);
    }

    private Vertex getVertex(Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        if (vertices.size() != 1) {
            undo(context);
            return null;
        }
        Vertex vertex = vertices.get(0);
        return vertex;
    }

}
