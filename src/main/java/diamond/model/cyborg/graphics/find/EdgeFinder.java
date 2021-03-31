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
public class EdgeFinder<T extends F<T>> extends Finder<T, Link<T>> {
    public EdgeFinder() {
    }

    @Override
    public Link<T> find(
            List<Face<T>> faces,
            Face<T> base,
            double x,
            double y,
            double scale) {
        for (Face<T> face : faces) {
            Link<T> link = face.find(this, x, y, scale);
            if (link != null) {
                return link;
            }
        }
        return null;
    }

    public Link<T> find(
            List<Link<T>> edges,
            Set<Seg<T>> creases,
            List<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return findFrom(edges, x, y, scale);
    }

    @Deprecated
    @Override
    public Link<T> find(
            Nodes<T> nodes,
            double x,
            double y,
            double scale) {
        return null;
    }

    @Deprecated
    @Override
    public Link<T> find(
            List<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return null;
    }

}
