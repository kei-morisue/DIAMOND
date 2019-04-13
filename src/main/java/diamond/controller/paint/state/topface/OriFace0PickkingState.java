/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.topface;

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
        setNextClass(this.getClass());
    }

    @Override
    protected void onResult(PaintContext context) {
        super.onResult(context);//TODO WIP
    }
}
