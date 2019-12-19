/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom4;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.CyborgPicker;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.HalfEdgeAdder;
import diamond.model.cyborg.util.PerpendicularUtil;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends HalfEdgePickingState {

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
        CyborgPicker picker = context.getPicker();
        Stack<Vertex> vertices = picker.getVertices();
        Stack<HalfEdge> halfEdges = picker.getHalfEdges();
        if (vertices.size() != 1) {
            context.initialize();
            return;
        }
        if (halfEdges.size() != 1) {
            context.initialize();
            return;
        }
        Vertex v = vertices.get(0);
        HalfEdge h = halfEdges.get(0);
        Double q = PerpendicularUtil.foot(v, h.getV0(), h.getV1());
        if (q != null) {
            HalfEdgeAdder.add(context, v, q);
            context.fold();
        }
        context.initialize();
    }

}
