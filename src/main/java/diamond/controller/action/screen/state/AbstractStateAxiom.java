/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
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

    public AbstractScreenState<T> execute(boolean isCtrl) {
        Step<T> step = context.getStep();
        if (isCtrl) {
            step.cut(axiom);
        } else {
            step.add(axiom);
        }
        return new StateIdle<T>(context);
    }

    @Override
    protected AbstractScreenState<T> onCtrl(boolean isLeft) {
        if (isLeft) {
            return execute(true);
        }
        return prevState;
    }

    @Override
    protected AbstractScreenState<T> on(boolean isLeft) {
        if (isLeft) {
            return execute(false);
        }
        return prevState;
    }

    @Deprecated
    @Override
    final public AbstractScreenState<T> leftCtrl(Ver<T> t) {
        return null;
    }

    @Deprecated
    @Override
    final public AbstractScreenState<T> leftCtrl(Seg<T> t) {
        return null;
    }

    @Deprecated
    @Override
    final public AbstractScreenState<T> left(Ver<T> t) {
        return null;
    }

    @Deprecated
    @Override
    final public AbstractScreenState<T> left(Seg<T> t) {
        return null;
    }

    protected abstract void cut();
}
