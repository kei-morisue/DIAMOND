/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.Set;

import diamond.model.cyborg.Pair;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.draw.SegDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class Crease<T extends F<T>> extends Seg<T> {

    @Deprecated
    public Crease() {
    }

    public Crease(Ver<T> p, Ver<T> q) {
        super(p, q);
    }

    public Crease(Edge<T> link) {
        this(link.p, link.q);
        nodes = link.nodes;
    }

    public Pair<Crease<T>> cut(Ver<T> r) {
        if (!isNode(r)) {
            return null;
        }
        Crease<T> sp = new Crease<T>(p, r);
        Crease<T> sq = new Crease<T>(r, q);
        cut(sp, r, sq);
        return new Pair<Crease<T>>(sp, sq);
    }

    public boolean isLeft(Seg<T> s) {
        return dir(s.c()).prod(dir().n()).isNeg();
    }

    public Ver<T> xPoint(Crease<T> s0) {
        Ver<T> v = findVer(s0);
        if (v != null) {
            return v;
        }
        return buildVer(s0);
    }

    //TODO toomuch workload???
    private Ver<T> buildVer(Crease<T> s0) {
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

    public void putNodes(Set<Crease<T>> segs) {
        for (Crease<T> s0 : segs) {
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

    @Override
    public void flip(Step<T> step) {
        step.cut(this);
    }
}
