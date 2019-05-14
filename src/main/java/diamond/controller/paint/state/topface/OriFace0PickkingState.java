/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.topface;

import java.util.Stack;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriFacePickkingState;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class OriFace0PickkingState extends OriFacePickkingState {

    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());
    }

    @Override
    protected void onResult(PaintContext context) {
        Stack<OriFace> pickedOriFaces = context.getPickedOriFaces();
        if (pickedOriFaces.size() != 1) {
            return;
        }
        OriFace picked = pickedOriFaces.get(0);
        //TODO implement face management
    }
}
