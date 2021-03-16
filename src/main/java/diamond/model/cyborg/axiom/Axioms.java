/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Axioms {
    public static <T extends F<T>> Seg<T> axiom1(Ver<T> v0, Ver<T> v1) {
        return new Seg<T>(v0, v1);
    }

    public static <T extends F<T>> Seg<T> axiom2(Ver<T> v0, Ver<T> v1) {
        Dir<T> d = v1.dir(v0).div(2);
        Ver<T> w0 = d.ver(v0);
        return new Seg<T>(
                ((Dir<T>) d.n().neg()).ver(w0),
                d.n().ver(w0));
    }

    public static <T extends F<T>> Seg<T> axiom4(Ver<T> v0, Ver<T> v1,
            Ver<T> v) {
        Dir<T> n = v1.dir(v0).n();
        Dir<T> d0 = v0.dir(v);
        F<T> prod = d0.prod(n);
        if (prod.isZero()) {
            return null;
        }
        F<T> alpha = prod.div(n.norm());
        Ver<T> w = ((Dir<T>) n.scale(alpha)).ver(v);
        return new Seg<T>(((Dir<T>) n.neg()).ver(w), n.ver(w));
    }

}
