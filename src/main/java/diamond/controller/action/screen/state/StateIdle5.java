/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.axiom.Axioms;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d1.Line;
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
    public AbstractScreenState<T> leftCtrl(Seg<T> s0) {
        return left(s0);
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> v) {
        return this;
    }

    @Override
    public AbstractScreenState<T> left(Seg<T> s0) {
        Line<T> axiom = Axioms.axiom5(v0, v, s0);
        if (axiom == null) {
            return this;
        }
        return new StateAxiom5<T>(context, this, v0, v, s0, axiom);
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();
        v0.draw(screen, g2d, scale, true);
        v.draw(screen, g2d, scale, true);
        if (pointedS != null) {
            pointedS.draw(screen, g2d, scale, true);
            Line<T> axiom = Axioms.axiom5(v0, v, pointedS);
            if (axiom != null) {
                axiom.draw(screen, g2d, scale, true);
            }
        }
    }

}
