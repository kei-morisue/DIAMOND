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
public class CreaseFinder<T extends F<T>> extends Finder<T, Crease<T>> {
    public CreaseFinder() {
    }

    @Override
    public Crease<T> find(
            List<Face<T>> faces,
            Face<T> base,
            double x,
            double y,
            double scale) {
        for (Face<T> face : faces) {
            Crease<T> seg = face.find(this, x, y, scale);
            if (seg != null) {
                return seg;
            }
        }
        return null;
    }

    @Deprecated
    @Override
    public Crease<T> find(
            Nodes<T> nodes,
            double x,
            double y,
            double scale) {
        return null;
    }

    @Deprecated
    @Override
    public Crease<T> find(
            List<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return null;
    }

    @Override
    public Crease<T> find(
            LoopedEdge<T> loop,
            Set<Crease<T>> creases,
            double x,
            double y,
            double scale) {
        return findFrom(creases, x, y, scale);
    }

    @Deprecated
    @Override
    public Crease<T> find(
            HashSet<Edge<T>> edges,
            LinkedList<Ver<T>> vers,
            double x,
            double y,
            double scale) {
        return null;
    }

}
