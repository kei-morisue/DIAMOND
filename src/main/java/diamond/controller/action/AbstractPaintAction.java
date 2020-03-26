/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPaintAction {
    private AbstractState state;

    public AbstractPaintAction() {
        setInitialState();
    }

    protected abstract void setInitialState();

    protected final void setActionState(AbstractState state) {
        this.state = state;
    }

    protected final AbstractState getState() {
        return state;
    }

    public void recover(Context context) {
    }

    public AbstractPaintAction onLeftClick(Context context) {
        doAction(context);
        return null;
    }

    public AbstractPaintAction onRightClick(Context context) {
        undo(context);
        return this;
    }

    public void doAction(Context context) {
        state = state.doAction(context);
    }

    public void undo(Context paintContext) {
        state = state.undoAction(paintContext);
    }

    public void onMove(Context context) {
        state.setPointer(context);
    }

    public void onPress(Context context) {
    }

    public void onDrag(Context context) {
    }

    public void onRelease(Context context) {
    }
}
