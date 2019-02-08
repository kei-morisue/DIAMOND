/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action.axiom2;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.action.AbstractPaintAction;
import diamond.controller.paint.state.axiom2.OriPoint0PickkingState;

/**
 * @author long_
 *
 */
public class Axiom2Action extends AbstractPaintAction {
    public Axiom2Action() {
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
