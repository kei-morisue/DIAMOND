/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Nodes;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class D1Finder {
    public static <T extends F<T>> Ver<T> findNode(
            Nodes<T> nodes,
            double x,
            double y,
            double scale) {
        return nodes.findNode(x, y, scale);
    }

    public static <T extends F<T>> Ver<T> findVer(
            Ver<T> p,
            Ver<T> q,
            double x, double y, double scale) {
        return (p.isNear(x, y, scale)) ? p : (q.isNear(x, y, scale)) ? q : null;
    }
}
