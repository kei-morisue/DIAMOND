/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.screen.state;

import java.awt.Graphics2D;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractScreenState {
    public <T extends F<T>> AbstractScreenState next(
            boolean isLeft,
            boolean isCtrl,
            Ver<T> v,
            D1<T> s) {
        if (isCtrl) {
            return (isLeft) ? leftCtrl(v, s) : rightCtrl(v, s);
        }
        return (isLeft) ? left(v, s) : right(v, s);
    };

    public abstract void draw(Graphics2D g2d);

    protected abstract <T extends F<T>> AbstractScreenState leftCtrl(
            Ver<T> v,
            D1<T> s);

    protected abstract <T extends F<T>> AbstractScreenState rightCtrl(
            Ver<T> v,
            D1<T> s);

    protected abstract <T extends F<T>> AbstractScreenState left(
            Ver<T> v,
            D1<T> s);

    protected abstract <T extends F<T>> AbstractScreenState right(
            Ver<T> v,
            D1<T> s);
}
