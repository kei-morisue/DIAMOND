/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d1.D1;
import diamond.model.cyborg.geom.d1.Link;
import diamond.model.cyborg.geom.d1.Loop;
import diamond.model.cyborg.geom.d1.Seg;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class FaceCuttable<T extends F<T>> extends AbstractFace<T> {
    @Deprecated
    public FaceCuttable() {
        super();
    }

    public FaceCuttable(Link<T> edge) {
        super(edge);
    }

    public Pair<Face<T>> cut(Seg<T> seg, Step<T> step) {
        Loop.cut(edges, seg, step);
        Pair<Face<T>> fg = cut(seg);
        add(seg, fg);
        return fg;
    }

    private Pair<Face<T>> cut(Seg<T> seg) {
        Link<T> link = new Link<T>(seg);
        Pair<Face<T>> fg = new Pair<Face<T>>(
                new Face<T>(link),
                new Face<T>(link));
        for (Link<T> edge : edges) {
            add(edge, seg, fg);
        }
        fg.p.order();
        fg.q.order();
        return fg;
    }

    private void add(Seg<T> seg, Pair<Face<T>> fg) {
        creases.remove(seg);
        for (Seg<T> crease : creases) {
            Ver<T> x = crease.findNode(seg);
            if (x != null) {
                Pair<Seg<T>> pair = crease.cut(x);
                add(pair.p, seg, fg);
                add(pair.q, seg, fg);
            } else {
                add(crease, seg, fg);
            }
        }
    }

    private void add(
            D1<T> s,
            Seg<T> seg,
            Pair<Face<T>> pq) {
        if (seg.isLeft(s)) {
            s.add(pq.p);
        } else {
            s.add(pq.q);
        }
    }
}
