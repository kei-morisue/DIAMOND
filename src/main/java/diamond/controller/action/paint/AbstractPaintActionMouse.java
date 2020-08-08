/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import diamond.controller.action.state.AbstractPaintState;
import diamond.controller.action.state.AbstractState;

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
    public final void onLeftClick() {
        state = state.doAction();
        setChanged();
        notifyObservers();
    }

    @Override
    public final void onRightClick() {
        state = state.undoAction();
        setChanged();
        notifyObservers();
    }

    @Override
    public final void onMove() {
        state.onMove();
    }

    @Override
    public final void onRightCtrlClick() {
    }

    @Override
    public final void onPress() {
    }

    @Override
    public final void onDrag() {
    }

    @Override
    public String getInfo() {
        String[] split = state.getClass().getName().split("\\.");
        return split[split.length - 1];
    }
}
