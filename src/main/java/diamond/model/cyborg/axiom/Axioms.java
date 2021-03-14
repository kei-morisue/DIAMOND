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
        return new Seg<T>(w0, d.n().ver(w0));
    }

}
