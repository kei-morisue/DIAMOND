/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateAxiom3<T extends F<T>> extends AbstractStateAxiom<T> {
    private Seg<T> s0;
    private Seg<T> s1;

    public StateAxiom3(
            Context<T> context,
            StateIdle34<T> stateIdol34,
            Seg<T> s0,
            Seg<T> s1,
            Line<T> axiom) {
        super(context, stateIdol34, axiom);
        this.s0 = s0;
        this.s1 = s1;
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        float scale = (float) screen.getScale();
        s0.draw(screen, g2d, scale, true);
        s1.draw(screen, g2d, scale, true);
        axiom.draw(screen, g2d, scale, false);
    }

    @Override
    protected void cut() {
        // TODO 自動生成されたメソッド・スタブ

    }

}
