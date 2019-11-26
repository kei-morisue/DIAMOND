/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractState {
    private Class<? extends AbstractState> nextStateClass;
    private Class<? extends AbstractState> prevStateClass;

    public AbstractState() {
        initialize();
    }

    protected void initialize() {
        setNextClass();
        setPrevClass();
    };

    abstract protected void setNextClass();

    abstract protected void setPrevClass();

    protected abstract void undo(Context context);

    protected abstract void aftermath(Context context);

    protected abstract boolean act(Context context);

    abstract public void setCandidate(Context context);

    public AbstractState doAction(Context context) {
        if (!act(context)) {
            return this;
        }
        aftermath(context);
        AbstractState nextState = getNextState();
        return nextState;
    }

    public final AbstractState undoAction(Context context) {
        undo(context);
        AbstractState prevState = getPreviousState();
        return prevState;
    }

    private AbstractState getNextState() {
        return createInstance(this.nextStateClass);
    }

    private AbstractState getPreviousState() {
        return createInstance(this.prevStateClass);
    }

    private AbstractState createInstance(
            Class<? extends AbstractState> c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return this;
        }
    }
}
