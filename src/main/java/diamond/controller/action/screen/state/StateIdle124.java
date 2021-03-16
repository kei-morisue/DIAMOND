/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.axiom.Axioms;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateIdle124<T extends F<T>> extends AbstractScreenState<T> {
    private Ver<T> v0;
    private Seg<T> axiom1;
    private Seg<T> axiom2;
    private Seg<T> axiom4;

    public StateIdle124(
            Context<T> context,
            StateIdle<T> stateIdol,
            Ver<T> t) {
        super(context, stateIdol);
        v0 = t;
    }

    @Override
    public AbstractScreenState<T> leftCtrl(Ver<T> t) {
        return new StateAxiom1<T>(context, this, v0, t);
    }

    @Override
    public AbstractScreenState<T> leftCtrl(D1<T> t) {
        if (axiom4 == null) {
            return this;
        }
        return new StateAxiom4<T>(context, this, v0, t, axiom4);
    }

    @Override
    public AbstractScreenState<T> left(Ver<T> t) {
        return new StateAxiom2<T>(context, this, v0, t);
    }

    @Override
    public AbstractScreenState<T> left(D1<T> t) {
        if (axiom4 == null) {
            return this;
        }
        return new StateAxiom4<T>(context, this, v0, t, axiom4);
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        v0.drawPointed(screen, g2d);
        if (pointedS != null) {
            pointedS.drawPointed(screen, g2d);
            axiom4 = Axioms.axiom4(pointedS.getP(), pointedS.getQ(), v0);
            if (axiom4 != null) {
                axiom4.drawPointed(screen, g2d);
            }
        }
        if (pointedV != null) {
            pointedV.drawPointed(screen, g2d);
            if (isCtrl) {
                axiom1 = Axioms.axiom1(v0, pointedV);
                axiom1.drawPointed(screen, g2d);
            } else {
                axiom2 = Axioms.axiom2(v0, pointedV);
                axiom2.drawPointed(screen, g2d);
            }
        }

    }

}
