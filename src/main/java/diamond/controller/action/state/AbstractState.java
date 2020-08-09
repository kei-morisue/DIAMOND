/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractState {
    protected AbstractState prev;
    protected AbstractState next;

    public void setNext(AbstractState next) {
        this.next = next;
    }

    public void setPrev(AbstractState prev) {
        this.prev = prev;
    }

    protected abstract void undo();

    protected abstract void executeAction();

    public abstract AbstractState onMove();

    protected abstract boolean tryAction();

    public abstract AbstractState onRelease();

    public final AbstractState doAction() {
        if (!tryAction()) {
            initialize();
            return this;
        }
        executeAction();
        return next;
    }

    abstract void initialize();

    public final AbstractState undoAction() {
        undo();
        return prev;
    }

}
