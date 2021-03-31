/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Set;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.draw.SegDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Seg<T extends F<T>> extends D1<T> {

    @Deprecated
    public Seg() {
    }

    public Seg(Ver<T> p, Ver<T> q) {
        super(p, q);
    }

    public LinkedList<Seg<T>> cut(Ver<T> r) {
        LinkedList<Seg<T>> segs = new LinkedList<Seg<T>>();
        if (!nodes.isNode(r)) {
            return segs;
        }
        Seg<T> sp = new Seg<T>(p, r);
        Seg<T> sq = new Seg<T>(r, q);
        nodes.cut(r, sp, sq);
        segs.add(sp);
        segs.add(sq);
        return segs;
    }

    public boolean isLeft(D1<T> s) {
        return dir(s.c()).prod(dir().n()).isNeg();
    }

    public Ver<T> xPoint(Seg<T> s0) {
        Ver<T> v = findVer(s0);
        if (v != null) {
            return v;
        }
        return buildVer(s0);
    }

    //TODO toomuch workload???
    private Ver<T> buildVer(Seg<T> s0) {
        Dir<T> d0 = s0.dir();
        Dir<T> d1 = dir();
        Dir<T> n = d1.n();
        F<T> den0 = n.prod(d0);
        if (den0.isZero()) {
            return null;
        }
        F<T> a = s0.dir(p).prod(n).div(den0);
        F<T> b = dir(s0.q).prod(n).div(den0);
        Dir<T> n0 = d0.n();
        F<T> den1 = n0.prod(d1);
        F<T> c = dir(s0.p).prod(n0).div(den1);
        F<T> d = s0.dir(q).prod(n0).div(den1);
        if (a.isNeg() &&
                b.isNeg() &&
                c.isNeg() &&
                d.isNeg()) {
            return ((Dir<T>) d0.scale(b)).ver(s0.q);
        }
        return null;
    }

    public void putNodes(Set<Seg<T>> segs) {
        for (Seg<T> s0 : segs) {
            Ver<T> x = s0.xPoint(this);
            if (x != null) {
                s0.add(x);
                add(x);
            }
        }
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        SegDrawer.draw(screen, g2d, scale, p, q, nodes, isPointed);
    }
}
