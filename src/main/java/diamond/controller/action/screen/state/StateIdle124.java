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
public final class StateIdle124<T extends F<T>> extends AbstractScreenState<T> {
    private Ver<T> v0;

    public StateIdle124(
            Context<T> context,
            StateIdle<T> stateIdol,
            Ver<T> t) {
        super(context, stateIdol);
        v0 = t;
    }

    @Override
    public AbstractScreenState<T> leftCtrl(Ver<T> t) {
        Line<T> axiom1 = Axioms.axiom1(v0, pointedV);
        if (axiom1 == null) {
            return this;
        }
        return new StateAxiom1<T>(context, this, v0, t, axiom1);
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> t) {
        Line<T> axiom2 = Axioms.axiom2(v0, t);
        if (axiom2 == null) {
            return this;
        }
        return new StateAxiom2<T>(context, this, v0, t, axiom2);
    }

    @Override
    public AbstractScreenState<T> leftCtrl(Seg<T> s) {
        return left(s);
    }

    @Override
    public AbstractScreenState<T> left(Seg<T> t) {
        Line<T> axiom4 = Axioms.axiom4(t, v0);
        if (axiom4 == null) {
            return this;
        }
        return new StateAxiom4<T>(context, this, v0, t, axiom4);
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();

        v0.draw(screen, g2d, scale, true);
        if (pointedS != null) {
            pointedS.draw(screen, g2d, scale, true);
            Line<T> axiom4 = Axioms.axiom4(pointedS, v0);
            if (axiom4 != null) {
                axiom4.draw(screen, g2d, scale, true);
            }
        }
        if (pointedV != null) {
            pointedV.draw(screen, g2d, scale, true);
            if (isCtrl) {
                Line<T> axiom1 = Axioms.axiom1(v0, pointedV);
                if (axiom1 != null) {
                    axiom1.draw(screen, g2d, scale, true);
                }
            } else {
                Line<T> axiom2 = Axioms.axiom2(v0, pointedV);
                if (axiom2 != null) {
                    axiom2.draw(screen, g2d, scale, true);
                }
            }
        }

    }

}
