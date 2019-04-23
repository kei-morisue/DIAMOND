/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.offset.AnyPointPickkingState;

/**
 * @author long_
 *
 */
public class OffsetAction extends AbstractPaintAction {

    public OffsetAction() {
        setActionState(new AnyPointPickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        //TODO alpha
        //TODO draw Rader
        //TODO draw tempLine
    }

}
