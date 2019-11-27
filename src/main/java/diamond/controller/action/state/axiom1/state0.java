/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom1;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;

/**
 * @author Kei Morisue
 *
 */
public class state0 extends VertexPickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = state1.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = state0.class;
    }

    @Override
    protected void aftermath(Context context) {

    }

}
