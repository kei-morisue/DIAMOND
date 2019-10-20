/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.context.PointedElement;
import diamond.controller.paint.state.mirror.OriLine0PickkingState;

/**
 * @author long_
 *
 */
public class MirrorAction extends AbstractPaintAction {
    public MirrorAction() {
        setActionState(new OriLine0PickkingState());
    }

    @Override
    public PaintActionInterface onRightClick(Context context) {
        undo(context);
        return this;
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        pickedElements.draw(g2d);
        pointedElements.draw(g2d);
    }

}
