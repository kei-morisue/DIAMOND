/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.LazyState;

/**
 * @author long_
 *
 */
public class LazyPaintAction extends AbstractPaintAction {
    public LazyPaintAction() {
        super();
        setActionState(new LazyState());
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
