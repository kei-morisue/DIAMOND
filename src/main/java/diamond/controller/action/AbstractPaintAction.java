/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.geom.Point2D;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPaintAction implements PaintActionInterface {

    private AbstractState state;

    protected final void setActionState(AbstractState state) {
        this.state = state;
    }

    protected final AbstractState getState() {
        return state;
    }

    @Override
    public void destroy(Context context) {
        context.initialize();
    }

    @Override
    public void recover(Context context) {
    }

    @Override
    public PaintActionInterface onRightClick(Context context) {
        undo(context);
        return this;
    }

    @Override
    public void doAction(Context context, Point2D.Double clickedPoint) {
        state = state.doAction(context);
    }

    @Override
    public void undo(Context paintContext) {
        state = state.undoAction(paintContext);
    }

    @Override
    public void onMove(Context context) {
        state.setCandidate(context);
    }

    @Override
    public void onPress(Context context) {
    };

    @Override
    public void onDrag(Context context) {
    };

    @Override
    public void onRelease(Context context) {
    };

}
