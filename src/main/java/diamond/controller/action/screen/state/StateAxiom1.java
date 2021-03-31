/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateAxiom1<T extends F<T>> extends AbstractStateAxiom<T> {
    private Ver<T> v0;
    private Ver<T> v1;

    public StateAxiom1(
            Context<T> context,
            StateIdle124<T> stateIdol124,
            Ver<T> v0,
            Ver<T> v1,
            Line<T> axiom) {
        super(context, stateIdol124, axiom);
        this.v0 = v0;
        this.v1 = v1;
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();
        v0.draw(screen, g2d, scale, true);
        v1.draw(screen, g2d, scale, true);
        axiom.draw(screen, g2d, scale, false);
    }

    @Override
    protected void cut() {
        // TODO 自動生成されたメソッド・スタブ

    }

}
