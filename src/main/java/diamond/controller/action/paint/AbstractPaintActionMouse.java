/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import diamond.controller.action.paint.state.AbstractPaintState;
import diamond.controller.action.paint.state.AbstractState;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPaintActionMouse extends AbstractPaintAction {
    protected AbstractState state;

    protected final void initialize(AbstractPaintState... states) {
        AbstractState state0 = this.state;
        for (AbstractState state1 : states) {
            if (this.state == null) {
                this.state = state1;
                state1.setPrev(state1);
            } else {
                state1.setPrev(state0);
                state0.setNext(state1);
            }
            state0 = state1;
        }
        state0.setNext(this.state);
    }

    @Override
    public void onLeftPress(boolean isCtrl) {
        if (isCtrl) {
            onLeftCtrl();
        } else {
            state = state.doAction();
            setChanged();
            notifyObservers();
        }
    }

    @Override
    public final void onRightPress(boolean isCtrl) {
        state = state.undoAction();
        setChanged();
        notifyObservers();
    }

    @Override
    public final void onMove() {
        state.onMove();
    }

    @Override
    public final void onRelease() {
        this.state = state.onRelease();
    }

    @Override
    public final void onDrag() {
    }

    @Override
    public String getInfo() {
        return state.toString();
    }
}
