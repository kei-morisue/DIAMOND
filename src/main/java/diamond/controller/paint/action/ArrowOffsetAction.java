/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.offsetarrow.Arrow0PickkingState;
import diamond.controller.paint.state.offsetarrow.ArrowOffsetState;
import diamond.view.screen.draw.RadarDrawer;

/**
 * @author long_
 *
 */
public class ArrowOffsetAction extends AbstractPaintAction {

    public ArrowOffsetAction() {
        setActionState(new Arrow0PickkingState());
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        if (getActionState().getClass() == ArrowOffsetState.class) {
            RadarDrawer.draw(g2d, paintScreenContext);
        } else {
            paintScreenContext.getPointedElements().draw(g2d);
        }
    }

}
