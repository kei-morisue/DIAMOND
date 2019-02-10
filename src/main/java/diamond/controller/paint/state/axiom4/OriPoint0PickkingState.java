/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.axiom4;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;

/**
 * @author long_
 *
 */
public class OriPoint0PickkingState extends OriPointPickkingState {

    public OriPoint0PickkingState() {
        super();
        initialize();
    }

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriLine0PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {

    }

}
