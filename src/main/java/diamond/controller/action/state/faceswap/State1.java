/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.faceswap;

import java.util.LinkedList;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.FacePickingState;
import diamond.model.cyborg.Face;

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
        insert(context, f0, f1);
        context.initialize();
    }

    private void insert(Context context, Face f0, Face f1) {
        LinkedList<Face> faces = context.getCp().getFaces();
        faces.remove(f0);
        int i1 = faces.indexOf(f1);
        faces.add(i1, f0);
    }

}
