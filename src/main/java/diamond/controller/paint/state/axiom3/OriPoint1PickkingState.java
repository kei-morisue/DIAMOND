/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom3;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;

/**
 * @author long_
 *
 */
public class OriPoint1PickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint2PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

}
