/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.faceorder;

import java.util.LinkedList;
import java.util.Stack;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriFacePickkingState;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class OriFace1PickkingState extends OriFacePickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriFace0PickkingState.class);
        setNextClass(OriFace0PickkingState.class);
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

    @Override
    protected void undoAction(PaintContext context) {
        if (context.getPickedOriFaces().size() == 1) {
            context.getPickedOriFaces().pop();
        }
    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriFace> picked = context.getPickedOriFaces();
        if (picked.size() == 2) {
            OriFace f0 = picked.get(0);
            OriFace f1 = picked.get(1);
            if (f0 != f1) {
                LinkedList<OriFace> faces = context.palette.getOriModel()
                        .getFaces();
                faces.remove(f0);
                int i1 = faces.indexOf(f1);
                faces.add(i1, f0);
            }
        }
        context.initialize();
    }
}
