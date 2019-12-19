/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.faceswap;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.FacePickingState;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.util.FaceUtil;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends FacePickingState {

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
        Stack<Face> faces = context.getPicker().getFaces();
        if (faces.size() != 2) {
            context.initialize();
            return;
        }
        Face f0 = faces.get(0);
        Face f1 = faces.get(1);
        FaceUtil.insert(context.getCp().getFaces(), f0, f1);
        context.initialize();
    }
}
