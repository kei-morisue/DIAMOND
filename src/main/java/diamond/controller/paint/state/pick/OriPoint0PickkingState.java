/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.pick;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public class OriPoint0PickkingState extends OripointPickkingState {
    /**
     *
     */
    public OriPoint0PickkingState() {
        super();
        initialize();
    }

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint1PickkingState.class);
    }

    @Override
    protected void onResult(PaintContext context) {

    }

}
