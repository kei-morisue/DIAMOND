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
public class Dir<T extends F<T>> extends M<Dir<T>, T> implements Serializable {
    public final F<T> x;
    public final F<T> y;

    public Dir(F<T> x, F<T> y) {
        this.x = x;
        this.y = y;
    }

    public Ver<T> ver(Ver<T> v0) {
        Ver<T> w0 = (Ver<T>) v0;
        return new Ver<T>(
                w0.x.add(x),
                w0.y.add(y));
    }

    @Override
    public M<Dir<T>, T> add(M<Dir<T>, T> m) {
        Dir<T> n = (Dir<T>) m;
        return new Dir<T>(x.add(n.x), y.add(n.y));
    }

    @Override
    public M<Dir<T>, T> neg() {
        return new Dir<T>(x.neg(), y.neg());
    }

    @Override
    public F<T> prod(M<Dir<T>, T> m) {
        Dir<T> n = (Dir<T>) m;
        return x.mul(n.y).add(y.mul(n.x));
    }

    @Override
    public M<Dir<T>, T> scale(F<T> f) {
        return new Dir<T>(x.mul(f), y.mul(f));
    }

}