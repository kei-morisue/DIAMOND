/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.topface;

import java.util.LinkedList;

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
        super.onResult(context);
        LinkedList<OriFace> faces = context.palette.getOriModel().getFaces();
        OriFace face = context.getPickedOriFaces().get(0);
        if (faces.getLast() != face) {
            faces.remove(face);
            faces.addLast(face);
        }
        {
            faces.remove(face);
            faces.addFirst(face);
        }
    }
}
