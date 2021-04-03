/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateAxiom4<T extends F<T>> extends AbstractStateAxiom<T> {
    private Ver<T> v;
    private Seg<T> s;

    public StateAxiom4(
            Context<T> context,
            AbstractScreenState<T> prevState,
            Ver<T> v,
            Seg<T> s,
            Line<T> axiom) {
        super(context, prevState, axiom);
        this.v = v;
        this.s = s;
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();
        v.draw(screen, g2d, scale, true);
        s.draw(screen, g2d, scale, true);
        axiom.draw(screen, g2d, scale, false);
    }

    @Override
    protected void cut() {
        // TODO 自動生成されたメソッド・スタブ

    }

}
