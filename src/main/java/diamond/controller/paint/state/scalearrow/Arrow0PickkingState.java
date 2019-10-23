/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.scalearrow;

import diamond.controller.paint.state.ArrowPickkingState;

/**
 * @author long_
 *
 */
public class Arrow0PickkingState extends ArrowPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(Arrow0PickkingState.class);
        setNextClass(ArrowScalingState.class);
    }

}
