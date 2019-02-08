/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.axiom1.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class Axiom1Action extends AbstractPaintAction {
    public Axiom1Action() {
        super();
        setActionState(new OriPoint0PickkingState());
    }

    @Override
    public void onPress(PaintContext context) {
    }

    @Override
    public void onDrag(PaintContext context) {
    }

    @Override
    public void onRelease(PaintContext context) {
    }

}
