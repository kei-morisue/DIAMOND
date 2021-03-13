/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d0;

import java.io.Serializable;

import diamond.model.math.field.F;
import diamond.model.math.metric.M;

/**
 * @author Kei Morisue
 *
 */
public class Dir<T extends F<T>> extends M<T> implements Serializable {
    public final F<T> x;
    public final F<T> y;

    public Dir(F<T> x, F<T> y) {
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

    @Override
    public M<T> add(M<T> m) {
        Dir<T> n = (Dir<T>) m;
        return new Dir<T>(x.add(n.x), y.add(n.y));
    }

    @Override
    public M<T> neg() {
        return new Dir<T>(x.neg(), y.neg());
    }

    @Override
    public F<T> prod(M<T> m) {
        Dir<T> n = (Dir<T>) m;
        return x.mul(n.x).add(y.mul(n.y));
    }

    @Override
    public M<T> scale(F<T> f) {
        return new Dir<T>(x.mul(f), y.mul(f));
    }

    public Dir<T> mul(int i) {
        return new Dir<T>(x.mul(i), y.mul(i));
    }

    public Dir<T> div(int i) {
        return new Dir<T>(x.div(i), y.div(i));
    }

}