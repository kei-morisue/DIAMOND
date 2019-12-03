/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.flipEdge;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.HalfEdgeFlipper;

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
        nextStateClass = State0.class;
    }

    @Override
    protected void aftermath(Context context) {
        HalfEdge he = context.getPicker().getHalfEdges().get(0);
        HalfEdgeFlipper.flip(he);
        context.initialize();
    }

}
