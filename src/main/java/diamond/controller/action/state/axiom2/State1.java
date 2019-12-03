/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom2;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.BisectorUtil;
import diamond.model.cyborg.util.HalfEdgeAdder;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends VertexPickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = State0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State1.class;
    }

    @Override
    protected void aftermath(Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        Vertex v0 = vertices.get(0);
        Vertex v1 = vertices.get(1);
        Double[] pts = BisectorUtil.perpendicular(v0, v1);
        HalfEdgeAdder.add(context, pts[0], pts[1]);
        context.initialize();
    }

}
