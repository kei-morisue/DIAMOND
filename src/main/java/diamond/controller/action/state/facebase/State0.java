/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.facebase;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.FacePickingState;
import diamond.model.cyborg.Face;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FacePickingState {

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
        Stack<Face> faces = context.getPicker().getFaces();
        if (faces.size() != 1) {
            context.initialize();
            return;
        }
        Face face = faces.get(0);
        context.getCp().setBaseFace(face);
        context.fold();
        context.initialize();
    }

}
