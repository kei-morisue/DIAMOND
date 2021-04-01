/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class Loop {
    //TODO ensure links are closed
    public static <T extends F<T>> LinkedList<Ver<T>> order(
            List<Link<T>> links) {
        LinkedList<Ver<T>> vers = new LinkedList<Ver<T>>();
        HashSet<Link<T>> target = new HashSet<Link<T>>(links);

        Link<T> l0 = links.get(0);
        Ver<T> r = l0.p;
        vers.add(r);
        for (int i = 0; i < links.size(); i++) {
            Link<T> l1 = find(target, r);
            if (l1 != null) {
                r = (l1.p == r) ? l1.q : l1.p;
                vers.add(r);
                l0 = l1;
                target.remove(l0);
            }
        }
        return vers;
    }

    public static <T extends F<T>> Pair<Link<T>> cut(
            Collection<Link<T>> edges,
            Seg<T> seg,
            Step<T> step) {
        Link<T> lp = cut(edges, seg.p, seg, step);
        Link<T> lq = cut(edges, seg.q, seg, step);
        return new Pair<Link<T>>(lp, lq);
    }

    private static <T extends F<T>> Link<T> find(
            Set<Link<T>> links,
            Ver<T> v) {
        for (Link<T> l1 : links) {
            if (l1.p == v || l1.q == v) {
                return l1;
            }
        }
        return null;
    }

    private static <T extends F<T>> Link<T> findNode(
            Collection<Link<T>> links,
            Ver<T> v) {
        for (Link<T> l : links) {
            if (l.isNode(v)) {
                return l;
            }
        }
        return null;
    }

    private static <T extends F<T>> Link<T> cut(
            Collection<Link<T>> edges,
            Ver<T> p,
            Seg<T> seg,
            Step<T> step) {
        Link<T> lp = findNode(edges, p);
        if (lp != null) {
            cut(seg, step, p, lp);
        }
        return lp;
    }

    private static <T extends F<T>> void cut(
            Seg<T> seg,
            Step<T> step,
            Ver<T> p,
            Link<T> lp) {
        Pair<Link<T>> pair = lp.cut(p);
        Pair<Face<T>> fg = step.findPair(lp);
        add(pair, fg.p, lp);
        add(pair, fg.q, lp);

    }

    private static <T extends F<T>> void add(
            Pair<Link<T>> pair,
            Face<T> f,
            Link<T> beRemoved) {
        if (f != null) {
            f.add(pair);
            f.remove(beRemoved);
        }
    }
}
