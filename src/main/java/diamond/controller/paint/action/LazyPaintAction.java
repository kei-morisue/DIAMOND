/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

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
    public void onDraw(Graphics2D g2d, PaintContext context) {
    }

}
