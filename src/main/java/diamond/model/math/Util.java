/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.math;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Util {
    public static double norm(double... ds) {
        double n = 0;
        for (double d : ds) {
            n = n + d * d;
        }
        return n;
    }

    public static <T extends F<T>> double footSquare(
            Ver<T> v0,
            Ver<T> v1,
            double x,
            double y) {
        Dir<T> dir = v1.dir(v0);
        Dir<T> n = dir.n();
        double prod = (x - v0.x.d()) * (n.x.d()) + (y - v0.y.d()) * (n.y.d());
        return prod * prod / n.norm().d();//TODO
    }
}
