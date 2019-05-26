/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.faceorder;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriFacePickkingState;

/**
 * @author long_
 *
 */
public class OriFace0PickkingState extends OriFacePickkingState {

    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(OriFace1PickkingState.class);
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
    }
}
