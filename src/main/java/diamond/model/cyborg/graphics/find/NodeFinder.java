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
import diamond.model.cyborg.geom.d1.Crease;
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

    @Deprecated
    @Override
    public Ver<T> find(
            LoopedEdge<T> loop,
            Set<Crease<T>> creases,
            double x,
            double y,
            double scale) {
        Ver<T> n = loop.find(this, x, y, scale);
        if (n != null) {
            return n;
        }
        for (Crease<T> s : creases) {
            n = s.find(this, x, y, scale);
            if (n != null) {
                return n;
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
        for (Edge<T> e : edges) {
            Ver<T> n = e.find(this, x, y, scale);
            if (n != null) {
                return n;
            }
        }
        return null;
    }

}
