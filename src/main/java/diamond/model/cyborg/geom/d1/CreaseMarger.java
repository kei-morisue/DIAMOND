/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.util.Collection;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class CreaseMarger {

    public static <T extends F<T>> void marge(
            Collection<Crease<T>> cs0,
            Collection<Crease<T>> cs1,
            Face<T> face) {
        face.add(cs0);
        face.add(cs1);
        for (Crease<T> c0 : cs0) {
            for (Crease<T> c1 : cs1) {
                Crease<T> marged = marge(c0, c1);
                if (marged != null) {
                    face.remove(c0);
                    face.remove(c1);
                    face.add(marged);
                }
            }
        }
    }

    private static <T extends F<T>> Crease<T> marge(
            Crease<T> s0,
            Crease<T> s1,
            Ver<T> p,
            Ver<T> r,
            Ver<T> q) {
        Crease<T> crease = new Crease<T>(p, q);
        crease.add(s1.nodes);
        crease.add(s0.nodes);
        crease.add(r);
        return crease;
    }

    public static <T extends F<T>> Crease<T> marge(
            Crease<T> s0,
            Crease<T> s1) {
        if (!s0.dir().n().prod(s1.dir()).isZero()) {
            return null;
        }
        if (s1.p == s0.p) {
            return marge(s0, s1, s1.q, s0.p, s0.q);
        }
        if (s1.q == s0.p) {
            return marge(s0, s1, s1.p, s0.p, s0.q);
        }
        if (s1.p == s0.q) {
            return marge(s0, s1, s1.q, s0.q, s0.p);
        }
        if (s1.q == s0.q) {
            return marge(s0, s1, s1.p, s0.q, s0.p);
        }
        return null;
    }
}
