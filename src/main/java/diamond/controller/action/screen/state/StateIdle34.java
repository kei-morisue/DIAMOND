/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.axiom.Axioms;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateIdle34<T extends F<T>> extends AbstractScreenState<T> {
    private Seg<T> s;

    public StateIdle34(
            Context<T> context,
            StateIdle<T> stateIdol,
            Seg<T> t) {
        super(context, stateIdol);
        s = t;
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> v) {
        Line<T> axiom4 = Axioms.axiom4(s, v);
        if (axiom4 == null) {
            return this;
        }
        return new StateAxiom4<T>(context, this, v, s, axiom4);
    }

    @Override
    public AbstractScreenState<T> left(Seg<T> s0) {
        Line<T> axiom3 = Axioms.axiom3(s, s0, isCtrl);
        if (axiom3 == null) {
            return this;
        }
        return new StateAxiom3<T>(context, this, s, s0, axiom3);
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();

        s.draw(screen, g2d, scale, true);
        if (pointedS != null) {
            pointedS.draw(screen, g2d, scale, true);
            Line<T> axiom3 = Axioms.axiom3(s, pointedS, isCtrl);
            if (axiom3 == null) {
                return;
            }
            axiom3.draw(screen, g2d, scale, true);
        }
        if (pointedV != null) {
            pointedV.draw(screen, g2d, scale, true);
            Line<T> axiom4 = Axioms.axiom4(s, pointedV);
            if (axiom4 == null) {
                return;
            }
            axiom4.draw(screen, g2d, scale, true);
        }
    }

}
