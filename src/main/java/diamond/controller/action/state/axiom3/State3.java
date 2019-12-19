/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom3;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.CyborgPicker;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.BisectorUtil;
import diamond.model.cyborg.util.HalfEdgeAdder;

/**
 * @author Kei Morisue
 *
 */
public class State3 extends HalfEdgePickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = State0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State2.class;
    }

    @Override
    protected void aftermath(Context context) {
        CyborgPicker picker = context.getPicker();
        Stack<Vertex> vertices = picker.getVertices();
        Stack<HalfEdge> halfEdges = picker.getHalfEdges();
        if (vertices.size() != 3) {
            context.initialize();
            return;
        }
        if (halfEdges.size() != 1) {
            context.initialize();
            return;
        }
        Vertex v0 = vertices.get(0);
        Vertex v = vertices.get(1);
        Vertex v1 = vertices.get(2);
        HalfEdge he = halfEdges.get(0);

        Double q = BisectorUtil.angle(v0, v, v1, he.getV0(), he.getV1());
        if (q != null) {
            HalfEdgeAdder.add(context, v, q);
            context.fold();
        }
        context.initialize();
    }

}
