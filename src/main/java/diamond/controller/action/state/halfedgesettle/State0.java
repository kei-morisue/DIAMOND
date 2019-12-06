/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.halfedgesettle;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
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
        HalfEdge he = context.getPicker().getHalfEdges().get(0);
        if (he.getType() == EdgeType.CUT || he.getType() == EdgeType.CREASE) {

        } else if (EdgeType.isSettled(he.getType())) {
            HalfEdgeModifier.unSettle(context, he);
            context.fold();
        } else {
            HalfEdgeModifier.settle(context, he);
            context.fold();
        }
        context.initialize();
    }

}