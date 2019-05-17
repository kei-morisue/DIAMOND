/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.arrow.ArrowScalingState;
import diamond.view.screen.draw.RadarDrawer;

/**
 * @author long_
 *
 */
public class ArrowScalingAction extends AbstractPaintAction {

    public ArrowScalingAction() {
        setActionState(new ArrowScalingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        RadarDrawer.draw(g2d, context);
    }

}
