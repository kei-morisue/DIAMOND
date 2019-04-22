/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.mirror.OriLine0PickkingState;

/**
 * @author long_
 *
 */
public class SelectLineAction extends AbstractPaintAction {
    public SelectLineAction() {
        setActionState(new OriLine0PickkingState());//TODO
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPickedLines(g2d, context);
        drawPointedLine(g2d, context);
    }

}
