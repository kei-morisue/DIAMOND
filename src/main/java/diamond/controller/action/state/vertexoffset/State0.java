/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexoffset;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends VertexPickingState {

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
        context.setPaintScreen("offset_v");
    }

}
