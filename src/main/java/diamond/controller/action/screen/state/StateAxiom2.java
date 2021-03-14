/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.axiom.Axioms;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateAxiom2<T extends F<T>> extends AbstractStateAxiom<T> {
    private Ver<T> v0;
    private Ver<T> v1;

    public StateAxiom2(
            Context<T> context,
            StateIdle124<T> stateIdol124,
            Ver<T> v0,
            Ver<T> v1) {
        super(context, stateIdol124);
        this.v0 = v0;
        this.v1 = v1;
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        v0.drawPointed(screen, g2d);
        v1.drawPointed(screen, g2d);
        Axioms.axiom2(v0, v1).drawPointed(screen, g2d);
        ;
    }

    @Override
    protected void cut() {
        // TODO 自動生成されたメソッド・スタブ

    }

}
