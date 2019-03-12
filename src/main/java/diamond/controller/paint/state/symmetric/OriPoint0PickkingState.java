/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.symmetric;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;

/**
 * @author long_
 *
 */
public class OriPoint0PickkingState extends OriPointPickkingState {
    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint1PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {

    }

}
