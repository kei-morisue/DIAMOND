/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.LoopedEdge;
import diamond.model.cyborg.geom.d1.Nodes;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class VerFinder<T extends F<T>> extends Finder<T, Ver<T>> {
    public VerFinder() {
    }

    public Ver<T> find(
            List<Face<T>> faces,
            Face<T> base,
            double x,
            double y,
            double scale) {
        for (Face<T> face : faces) {
            Ver<T> ver = face.find(this, x, y, scale);
            if (ver != null) {
                return ver;
            }
        }
        return null;
    }

    public Ver<T> find(
            Ver<T> p,
            Ver<T> q,
            double x,
            double y,
            double scale) {
        if (findFrom(p, x, y, scale) != null) {
            return p;
        }
        if (findFrom(q, x, y, scale) != null) {
            return q;
        }
        return null;
    }

    @Deprecated
    @Override
    public Ver<T> find(
            Nodes<T> nodes,
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

    @Override
    public Ver<T> find(
            LoopedEdge<T> loop,
            Set<Seg<T>> creases,
            double x,
            double y,
            double scale) {
        Ver<T> v = loop.find(this, x, y, scale);
        if (v != null) {
            return v;
        }
        for (Seg<T> seg : creases) {
            v = seg.find(this, x, y, scale);
            if (v != null) {
                return v;
            }
        }
        return null;
    }

    @Override
    public Ver<T> find(
            HashSet<Edge<T>> edges,
            LinkedList<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return findFrom(vers, x, y, scale);
    }

}
