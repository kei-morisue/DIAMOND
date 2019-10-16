/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.arrow.ArrowPaintState;
import diamond.model.geom.element.diagram.arrow.AbstractArrow;

/**
 * @author long_
 *
 */
public class ArrowPaintAction extends AbstractPaintAction {
    public ArrowPaintAction(Class<? extends AbstractArrow> arrowClass) {
        setActionState(new ArrowPaintState(arrowClass));
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        drawPointedLine(g2d, context);

    }

}
