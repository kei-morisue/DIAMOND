/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import java.util.Collection;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.Crease;
import diamond.model.cyborg.geom.d1.Edge;
import diamond.model.cyborg.geom.d1.LoopedEdge;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class FaceCutter {

    public static <T extends F<T>> Pair<Face<T>> cut(
            LoopedEdge<T> loop,
            Collection<Crease<T>> creases,
            Crease<T> seg,
            Step<T> step) {
        loop.cut(seg, step);
        Pair<Face<T>> fg = cut(loop, seg);
        add(creases, seg, fg);
        return fg;
    }

    private static <T extends F<T>> Pair<Face<T>> cut(
            LoopedEdge<T> loop,
            Crease<T> seg) {
        Edge<T> link = new Edge<T>(seg);
        Pair<Face<T>> fg = new Pair<Face<T>>(
                new Face<T>(link),
                new Face<T>(link));
        loop.add(seg, fg);
        return fg;
    }

    private static <T extends F<T>> void add(
            Collection<Crease<T>> creases,
            Crease<T> seg,
            Pair<Face<T>> fg) {
        creases.remove(seg);
        for (Crease<T> crease : creases) {
            Ver<T> x = crease.findNode(seg);
            if (x != null) {
                Pair<Crease<T>> pair = crease.cut(x);
                add(pair.p, seg, fg);
                add(pair.q, seg, fg);
            } else {
                add(crease, seg, fg);
            }
        }
    }

    private static <S extends F<S>> void add(
            Crease<S> s,
            Crease<S> seg,
            Pair<Face<S>> pq) {
        if (seg.isLeft(s)) {
            pq.p.add(s);
            ;
        } else {
            pq.q.add(s);
        }
    }
}
