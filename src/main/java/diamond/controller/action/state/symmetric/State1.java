/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symmetric;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.CyborgPicker;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.HalfEdgeAdder;
import diamond.model.cyborg.util.HalfEdgeMirror;

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
        Stack<HalfEdge> hes = picker.getHalfEdges();
        if (hes.size() != 2) {
            context.initialize();
            return;
        }
        HalfEdge h0 = hes.get(0);
        HalfEdge h1 = hes.get(1);
        Vertex v0 = null;
        Vertex v = null;
        Vertex v1 = null;

        if (h0.getV0() == h1.getV0()) {
            v0 = h0.getV1();
            v = h0.getV0();
            v1 = h1.getV1();
        } else if (h0.getV0() == h1.getV1()) {
            v0 = h0.getV1();
            v = h0.getV0();
            v1 = h1.getV0();
        } else if (h0.getV1() == h1.getV0()) {
            v0 = h0.getV0();
            v = h0.getV1();
            v1 = h1.getV1();
        } else if (h0.getV1() == h1.getV1()) {
            v0 = h0.getV0();
            v = h0.getV1();
            v1 = h1.getV0();
        } else {
            context.initialize();
            return;
        }
        Double q = HalfEdgeMirror.get(v0, v, v1);
        if (q != null) {
            HalfEdgeAdder.add(context, v, q);
            context.fold();
        }
        context.initialize();
    }
}
