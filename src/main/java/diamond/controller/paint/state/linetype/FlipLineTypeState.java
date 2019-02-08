/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.linetype;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriLinePickkingState;

/**
 * @author long_
 *
 */
public class FlipLineTypeState extends OriLinePickkingState {

    public FlipLineTypeState() {
        super();
        initialize();
    }

    @Override
    protected void initialize() {
        setNextClass(FlipLineTypeState.class);
        setPrevClass(FlipLineTypeState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
        context.getPickedLines().get(0).flipType();
        context.getPickedLines().clear();
    }

}
