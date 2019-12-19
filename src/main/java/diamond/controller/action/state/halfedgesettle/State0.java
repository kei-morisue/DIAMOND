/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.halfedgesettle;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.HalfEdgeModifier;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends HalfEdgePickingState {

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
        Stack<HalfEdge> halfEdges = context.getPicker().getHalfEdges();
        if (halfEdges.size() != 1) {
            context.initialize();
            return;
        }
        HalfEdge he = halfEdges.get(0);
        if (he.getType() == EdgeType.CUT) {

        } else {
            Cp cp = context.getCp();
            if (EdgeType.isSettled(he.getType())) {
                HalfEdgeModifier.unSettle(cp, he);
                context.fold();
            } else {
                HalfEdgeModifier.settle(cp, he);
                context.fold();
            }
        }
        context.initialize();
    }

}
