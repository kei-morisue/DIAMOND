/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.find;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.LoopedEdge;
import diamond.model.cyborg.geom.d1.MV;
import diamond.model.cyborg.geom.d1.Nodes;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class Finder<T extends F<T>, S extends Graphic<T>> {

    public abstract S find(
            List<Face<T>> faces,
            Face<T> base,
            double x,
            double y,
            double scale);

    public abstract S find(
            LoopedEdge<T> loop,
            Set<Crease<T>> creases,
            Set<MV<T>> mvs,
            double x,
            double y,
            double scale);

    public abstract S find(
            HashSet<Edge<T>> edges,
            LinkedList<Ver<T>> vers,
            double x,
            double y,
            double scale);

    public abstract S find(
            Nodes<T> nodes,
            double x,
            double y,
            double scale);

    public abstract S find(
            List<Ver<T>> vers,
            double x,
            double y,
            double scale);

    public S findFrom(
            Collection<S> candidates,
            double x,
            double y,
            double scale) {
        for (S candidate : candidates) {
            if (candidate.isNear(x, y, scale)) {
                return candidate;
            }
        }
        return null;
    }

    public S findFrom(
            S candidate,
            double x,
            double y,
            double scale) {
        return (candidate.isNear(x, y, scale)) ? candidate : null;
    }

}
