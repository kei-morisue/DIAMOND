/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.pick;

import diamond.controller.paint.state.OripointPickkingState;
import diamond.view.paint.PaintContext;

/**
 * @author long_
 *
 */
public class Oripoint0PickkingState extends OripointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(Oripoint0PickkingState.class);
        setNextClass(Oripoint0PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {

    }

}
