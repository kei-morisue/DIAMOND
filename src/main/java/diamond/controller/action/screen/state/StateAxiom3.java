/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.controller.Context;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

/**
 * @author Kei Morisue
 *
 */
public final class StateAxiom3<T extends F<T>> extends AbstractStateAxiom<T> {
    private D1<T> s0;
    private D1<T> s1;
    private Line<T> axiom;

    public StateAxiom3(
            Context<T> context,
            StateIdle34<T> stateIdol34,
            D1<T> s0,
            D1<T> s1,
            Line<T> axiom) {
        super(context, stateIdol34);
        this.s0 = s0;
        this.s1 = s1;
        this.axiom = axiom;
    }

    @Override
    public void drawModel(ScreenModel<T> screen, Graphics2D g2d) {
        s0.drawPointed(screen, g2d);
        s1.drawPointed(screen, g2d);
        axiom.draw(screen, g2d);
    }

    @Override
    protected void cut() {
        // TODO 自動生成されたメソッド・スタブ

    }

}
