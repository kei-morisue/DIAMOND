/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.PaintContext;

/**
 * @author long_
 *
 */
public abstract class AbstractPaintState implements PaintStateInterface {

    private Class<? extends PaintStateInterface> nextStateClass;
    private Class<? extends PaintStateInterface> prevStateClass;

    public AbstractPaintState() {
        initialize();
    }

    protected abstract void initialize();

    abstract protected void undoAction(PaintContext context);

    protected abstract void onResult(PaintContext context);

    protected abstract void rebuild(PaintContext context);

    protected abstract boolean onAction(
            PaintContext context,
            Point2D.Double currentPoint);

    @Override
    public PaintStateInterface doAction(
            PaintContext context,
            Double currentPoint) {
        if (!onAction(context, currentPoint)) {
            return this;
        }
        onResult(context);
        rebuild(context);
        PaintStateInterface nextState = getNextState();
        return nextState;
    }

    @Override
    public final PaintStateInterface undo(PaintContext context) {
        undoAction(context);
        PaintStateInterface prevState = getPreviousState();
        return prevState;
    }

    private PaintStateInterface createInstance(
            Class<? extends PaintStateInterface> c) {
        PaintStateInterface stateInterface = null;
        try {
            stateInterface = c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            stateInterface = this;//TODO tricky implementation
        }
        return stateInterface;
    }

    protected void setNextClass(Class<? extends PaintStateInterface> next) {
        this.nextStateClass = next;
    }

    protected void setPrevClass(Class<? extends PaintStateInterface> prev) {
        this.prevStateClass = prev;
    }

    @Override
    public void setNextState(PaintStateInterface state) {
        this.nextStateClass = state.getClass();
    }

    @Override
    public void setPreviousState(PaintStateInterface state) {
        this.prevStateClass = state.getClass();
    }

    @Override
    public PaintStateInterface getNextState() {
        return createInstance(this.nextStateClass);
    }

    @Override
    public PaintStateInterface getPreviousState() {
        return createInstance(this.prevStateClass);
    }

}
