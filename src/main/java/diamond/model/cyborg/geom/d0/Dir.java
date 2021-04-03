/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import diamond.model.math.field.F;
import diamond.model.math.metric.M;

/**
 * @author Kei Morisue
 *
 */
public class Dir<T extends F<T>> extends M<Dir<T>, T> {
    public final T x;
    public final T y;

    public Dir(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public Ver<T> ver(Ver<T> v0) {
        return new Ver<T>(
                v0.x.add(x),
                v0.y.add(y));
    }

    public Dir<T> n() {
        return new Dir<T>(y.neg(), x);
    }

    public Dir<T> u() {
        return this.scale(norm().sqrt().inv());
    }

    public Dir<T> mul(int i) {
        return new Dir<T>(x.mul(i), y.mul(i));
    }

    public Dir<T> div(int i) {
        return new Dir<T>(x.div(i), y.div(i));
    }

    @Override
    public Dir<T> add(Dir<T> m) {
        return new Dir<T>(x.add(m.x), y.add(m.y));
    }

    @Override
    public Dir<T> neg() {
        return new Dir<T>(x.neg(), y.neg());
    }

    @Override
    public T prod(Dir<T> m) {
        return x.mul(m.x).add(y.mul(m.y));
    }

    @Override
    public Dir<T> scale(T f) {
        return new Dir<T>(x.mul(f), y.mul(f));
    }

    @Override
    public T norm() {
        return prod(this);
    }

}