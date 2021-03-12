/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public final class StateIdol extends AbstractScreenState {

    @Override
    protected <T extends F<T>> AbstractScreenState leftCtrl(Ver<T> v,
            Seg<T> s) {
        // TODO 自動生成されたメソッド・スタブ
        return this;
    }

    @Override
    protected <T extends F<T>> AbstractScreenState rightCtrl(Ver<T> v,
            Seg<T> s) {
        // TODO 自動生成されたメソッド・スタブ
        return this;
    }

    @Override
    protected <T extends F<T>> AbstractScreenState left(Ver<T> v, Seg<T> s) {
        // TODO 自動生成されたメソッド・スタブ
        return this;
    }

    @Override
    protected <T extends F<T>> AbstractScreenState right(Ver<T> v, Seg<T> s) {
        // TODO 自動生成されたメソッド・スタブ
        return this;
    }

    @Override
    public void draw(Graphics2D g2d) {
        //TODO
    }

}
