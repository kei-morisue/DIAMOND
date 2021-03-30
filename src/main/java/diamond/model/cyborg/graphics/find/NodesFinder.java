/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class NodesFinder {
    public static <T extends F<T>> Ver<T> findNode(
            List<Ver<T>> nodes,
            double x,
            double y,
            double scale) {
        return Finder.find(nodes, x, y, scale);
    }
}
