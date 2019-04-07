/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.baseface;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriFacePickkingState;
import diamond.model.geom.element.origami.OriFace;

/**
 * @author long_
 *
 */
public class BaseFacePickkingState extends OriFacePickkingState {
    @Override
    protected void onResult(PaintContext context) {
        super.onResult(context);
        OriFace baseFace = context.pointedOriFace;
        if (baseFace != null) {
            context.getCp().getOriModel().setBaseFace(baseFace);
        }
    }

    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());
    }
}
