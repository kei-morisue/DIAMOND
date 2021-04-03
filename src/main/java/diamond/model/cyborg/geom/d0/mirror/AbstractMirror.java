/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0.mirror;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractMirror<T extends F<T>> implements Mirror<T> {
    protected Ver<T> b;
    protected boolean isFlip = true;
    protected T cos;
    protected T sin;

    // M^((1-f)/2).R(t).v0 + B : Affine Transform
    protected AbstractMirror() {
    }

    @Override
    public Ver<T> apply(Ver<T> v) {
        return applyA(v).ver(b);
    }

    protected Dir<T> applyA(Ver<T> v) {
        T x = v.x;
        T y = v.y;
        return new Dir<T>(
                x.mul(cos).sub(y.mul(sin)),
                flip(x.mul(sin).add(y.mul(cos))));
    }

    protected T flip(T x) {
        return (isFlip) ? x.neg() : x;
    }

    @Deprecated
    public boolean isFlip() {
        return isFlip;
    }

    @Deprecated
    public void setFlip(boolean isFlip) {
        this.isFlip = isFlip;
    }

}
