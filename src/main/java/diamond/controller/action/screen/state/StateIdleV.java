/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateIdleV<T extends F<T>> extends AbstractScreenState<T> {
    private Ver<T> v0;

    public StateIdleV(
            Context<T> context,
            StateIdle<T> stateIdol,
            Ver<T> t) {
        super(context, stateIdol);
        v0 = t;
    }

    @Override
    public AbstractScreenState<T> leftCtrl(Ver<T> t) {
        return new StateIdle5<T>(context, this, v0, t);
    }

    @Override
    public AbstractScreenState<T> leftCtrl(D1<T> t) {
        return this;
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> t) {
        return new StateIdle5<T>(context, this, v0, t);
    }

    @Override
    public AbstractScreenState<T> left(D1<T> t) {
        return this;
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        v0.draw(screen, g2d, true);
        if (pointedV != null) {
            pointedV.draw(screen, g2d, true);
        }

    }

}
