/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.List;
import java.util.Set;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Nodes;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class NodeFinder<T extends F<T>> extends Finder<T, Ver<T>> {
    public NodeFinder() {
    }

    public Ver<T> find(
            Nodes<T> nodes,
            double x,
            double y,
            double scale) {
        return nodes.find(this, x, y, scale);
    }

    @Deprecated
    @Override
    public Ver<T> find(
            List<Link<T>> edges,
            Set<Seg<T>> creases,
            List<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return null;
    }

    @Deprecated
    @Override
    public Ver<T> find(
            List<Face<T>> faces,
            Face<T> base,
            double x,
            double y,
            double scale) {
        return null;
    }

    @Override
    public Ver<T> find(
            List<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return findFrom(vers, x, y, scale);
    }

}
