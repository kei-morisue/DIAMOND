/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.axiom;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Line;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;
import diamond.model.math.field.Quad;

/**
 * @author Kei Morisue
 *
 */
public class Axioms {
    public static <T extends F<T>> Line<T> axiom1(Ver<T> v0, Ver<T> v1) {
        if (v0 == v1) {
            return null;
        }
        return new Line<T>(v0, v1);
    }

    public static <T extends F<T>> Line<T> axiom2(Ver<T> v0, Ver<T> v1) {
        if (v0 == v1) {
            return null;
        }
        Dir<T> d = v1.dir(v0).div(2);
        Ver<T> w0 = d.ver(v0);
        return new Line<T>(
                d.n().neg().ver(w0),
                d.n().ver(w0));
    }

    //TODO refactor!!!
    //TODO give another solution!!!
    public static <T extends F<T>> Line<T> axiom3(
            Seg<T> s0,
            Seg<T> s1,
            boolean alt) {
        if (s0 == s1) {
            return null;
        }
        Ver<T> w = s0.findPQ(s1);
        Dir<T> d0 = s0.dir();
        Dir<T> d1 = s1.dir();
        if (w != null) {
            Dir<T> u0 = s0.dir(w).scale(s1.dir(w).norm().sqrt());
            Dir<T> u1 = s1.dir(w).scale(s0.dir(w).norm().sqrt());
            return new Line<T>(w, u0.add(u1).ver(w));
        }
        w = s0.findNode(s1);
        if (w != null) {
            Dir<T> u0 = s0.dir(w).scale(s1.dir(w).norm().sqrt());
            Dir<T> u1 = s1.dir(w).scale(s0.dir(w).norm().sqrt());
            Dir<T> d = (alt) ? u0.add(u1) : u0.sub(u1);
            return new Line<T>(w, d.ver(w));
        }
        Dir<T> n0 = d0.n();
        Dir<T> n1 = d1.n();
        if (n0.prod(d1).isZero()) {
            Ver<T> v0 = s0.getP();
            Ver<T> v1 = s1.getP();
            w = v1.dir(v0).div(2).ver(v0);
            return new Line<T>(d1.neg().ver(w), d1.ver(w));
        }
        T beta = s0.dir(s1).prod(n0).div(d1.prod(n0));
        T alpha = s1.dir(s0).prod(n1).div(d0.prod(n1));
        w = d1.scale(beta).ver(s1.getP());
        Dir<T> u0 = s0.dir(w).scale(s1.dir(w).norm().sqrt());
        Dir<T> u1 = s1.dir(w).scale(s0.dir(w).norm().sqrt());
        if (beta.isNeg()) {
            if (!alpha.isNeg()) {
                u1 = u1.neg();
            }
        }
        return new Line<T>(u1.add(u0).ver(w), u1.neg().sub(u0).ver(w));
    }

    public static <T extends F<T>> Line<T> axiom4(Seg<T> s, Ver<T> v) {
        Dir<T> n = s.dir().n();
        Dir<T> d0 = s.dir(v);
        F<T> prod = d0.prod(n);
        if (prod.isZero()) {
            return null;
        }
        T alpha = prod.div(n.norm());
        Ver<T> w = n.scale(alpha).ver(v);
        return new Line<T>(n.neg().ver(w), n.ver(w));
    }

    public static <T extends F<T>> Line<T> axiom5(
            Ver<T> v0,
            Ver<T> v,
            Seg<T> s,
            boolean alt) {
        if (v0 == v) {
            return null;
        }
        Dir<T> d0 = s.dir();
        Dir<T> d1 = s.dir(v0);
        Dir<T> d2 = v.dir(v0);
        T a = d0.norm();
        T b = d0.prod(d1).mul(2);
        T c = d1.norm().sub(d2.norm());
        T root0 = Quad.root0(a, b, c);
        if (root0 == null) {
            return null;
        }
        T root1 = Quad.root1(root0, a, c);
        T alpha = (alt) ? root0 : root1;
        Dir<T> d3 = d1.add(d0.scale(alpha));
        return new Line<T>(v0, d3.add(d2).ver(v0));
    }

}
