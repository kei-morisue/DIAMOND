/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Util {
    public static double norm(double d0, double d1) {
        return d0 * d0 + d1 * d1;
    }

    public static <T extends F<T>> double footSquare(
            Ver<T> v0,
            Ver<T> v1,
            double x,
            double y) {
        Dir<T> d = v1.dir(v0);
        Dir<T> n = d.n();
        double prod = (x - v0.x.d()) * (n.x.d()) + (y - v0.y.d()) * (n.y.d());
        return prod * prod / n.norm().d();
    }

    public static <T extends F<T>> T footSquare(
            Ver<T> v0,
            Ver<T> v1,
            Ver<T> v) {
        Dir<T> d = v1.dir(v0);
        Dir<T> n = d.n();
        T prod = v.dir(v0).prod(n);
        return prod.mul(prod).div(n.norm());
    }

    public static <T extends F<T>> Ver<T> foot(
            Ver<T> v0,
            Ver<T> v1,
            Ver<T> v) {
        Dir<T> d = v1.dir(v0);
        Dir<T> n = d.n();
        Dir<T> k = n.scale(n.prod(v0.dir(v))).scale(n.norm().inv());
        return k.ver(v);
    }

}
