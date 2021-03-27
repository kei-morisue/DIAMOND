/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractStateAxiom<T extends F<T>>
        extends AbstractScreenState<T> {
    protected Line<T> axiom;

    protected AbstractStateAxiom(
            Context<T> context,
            AbstractScreenState<T> prevState,
            Line<T> axiom) {
        super(context, prevState);
        this.axiom = axiom;
    }

    public AbstractScreenState<T> execute(boolean isLeft) {
        context.getStep().add(axiom);
        cut();
        return new StateIdle<T>(context);
    }

    @Override
    protected AbstractScreenState<T> onLeft() {
        return execute(true);
    }

    @Override
    protected AbstractScreenState<T> onCtrl(boolean isleft) {
        if (isleft) {
            return execute(false);
        }
        return prevState;
    }

    @Override
    final public AbstractScreenState<T> leftCtrl(Ver<T> t) {
        return null;
    }

    @Override
    final public AbstractScreenState<T> leftCtrl(D1<T> t) {
        return null;
    }

    @Override
    final public AbstractScreenState<T> left(Ver<T> t) {
        return null;
    }

    @Override
    final public AbstractScreenState<T> left(D1<T> t) {
        return null;
    }

    protected void put(Line<T> axiom) {
        cut();
    }

    protected abstract void cut();
}
