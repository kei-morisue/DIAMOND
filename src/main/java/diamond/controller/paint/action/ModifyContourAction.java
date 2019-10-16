/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.modifycontour.OriLine0PickkingState;

/**
 * @author long_
 *
 */
public class ModifyContourAction extends AbstractPaintAction {
    public ModifyContourAction() {
        super();
        setActionState(new OriLine0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedLine(g2d, context);
        drawPickedLines(g2d, context);
    }

}
