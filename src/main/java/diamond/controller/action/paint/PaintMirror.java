/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.controller.action.state.AddSegment;
import diamond.controller.action.state.PickCyborg;
import diamond.model.cyborg.geom.d1.SegmentBase;

/**
 * @author Kei Morisue
 *
 */
public class PaintMirror extends AbstractPaintActionMouse {

    public PaintMirror(Context context) {
        initialize(
                new PickCyborg<SegmentBase>(context, SegmentBase.class),
                new AddSegment(context));
    }

    @Override
    protected void onLeftCtrl() {
        state = state.doAction();
        setChanged();
        notifyObservers();
    }

    @Override
    public void onLeftPress(boolean isCtrl) {
        state.doAction();
        setChanged();
        notifyObservers();
    }

    @Override
    protected void onRightCtrl() {
    }

    @Override
    public void onDraw(Graphics2D g2d) {

    }

}
