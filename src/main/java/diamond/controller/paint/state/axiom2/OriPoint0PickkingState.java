/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom2;

import diamond.controller.paint.context.Context;
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
    protected void onResult(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
    }

}
