/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.List;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.draw.LineDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public class Line<T extends F<T>> {
    private Ver<T> p;
    private Dir<T> d;
    private Dir<T> n;
    private Ver<T> pFar;
    private Ver<T> qFar;

    @Deprecated
    public Line() {
    }

    public Line(Ver<T> p, Ver<T> q) {
        this.p = p;
        d = q.dir(p);
        n = d.n();
        Ver<T> c = d.div(2).ver(p);
        pFar = p.dir(c).mul(100).ver(c);
        qFar = q.dir(c).mul(100).ver(c);
    }

    public boolean isOn(Ver<T> v) {
        return p.dir(v).prod(n).isZero();
    }

    public Seg<T> clip(List<Link<T>> links) {
        Ver<T> p = null;
        Ver<T> q = null;
        for (Link<T> link : links) {
            Ver<T> x = xPoint(link);
            if (x != null) {
                link.add(x);
                if (p == null) {
                    p = x;
                } else if (q == null) {
                    q = x;
                }
            }
        }
        if (p == null || q == null) {
            return null;
        }
        return new Seg<T>(p, q);
    }

    private Ver<T> xPoint(D1<T> s0) {
        if (isOn(s0.p)) {
            return s0.p;
        }
        Ver<T> x = s0.nodes.xNode(this);
        if (x != null) {
            return x;
        }
        Dir<T> d0 = s0.dir();
        F<T> den0 = d0.prod(n);
        if (den0.isZero()) {
            return null;
        }
        F<T> a = s0.p.dir(p).prod(n).div(den0);
        F<T> b = p.dir(s0.q).prod(n).div(den0);
        if (a.isNeg() && b.isNeg()) {
            return ((Dir<T>) d0.scale(b)).ver(s0.q);
        }
        return null;
    }

    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            boolean isPointed) {
        LineDrawer.draw(screen, g2d, pFar, qFar, isPointed);
    }

}
