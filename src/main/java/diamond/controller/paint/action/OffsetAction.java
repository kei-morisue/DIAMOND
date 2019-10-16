/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.PaintContext;
import diamond.controller.paint.state.offset.OffsetSettingState;
import diamond.view.screen.draw.RadarDrawer;

/**
 * @author long_
 *
 */
public class OffsetAction extends AbstractPaintAction {

    public OffsetAction() {
        setActionState(new OffsetSettingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
        RadarDrawer.draw(g2d, context);
    }

}
