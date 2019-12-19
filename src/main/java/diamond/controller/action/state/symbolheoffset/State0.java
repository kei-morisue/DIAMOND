/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.symbolheoffset;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends HalfEdgePickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = State1.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State0.class;
    }

    @Override
    protected void aftermath(Context context) {
        context.setPaintScreen("offset_s");
    }

}
