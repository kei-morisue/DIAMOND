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
public final class StateIdle5<T extends F<T>> extends AbstractScreenState<T> {
    private Ver<T> v0;
    private Ver<T> v;

    public StateIdle5(
            Context<T> context,
            StateIdleV<T> stateIdol,
            Ver<T> v0,
            Ver<T> v) {
        super(context, stateIdol);
        this.v0 = v0;
        this.v = v;
    }

    @Override
    public AbstractScreenState<T> leftCtrl(Ver<T> v) {
        return this;
    }

    @Override
    public AbstractScreenState<T> leftCtrl(D1<T> s0) {
        return new StateAxiom5<T>(context, this, v0, v, s0);
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> v) {
        return this;
    }

    @Override
    public AbstractScreenState<T> left(D1<T> s0) {
        return new StateAxiom5<T>(context, this, v0, v, s0);
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        v0.drawPointed(screen, g2d);
        v.drawPointed(screen, g2d);
        if (pointedS != null) {
            pointedS.drawPointed(screen, g2d);
        }
    }

}
