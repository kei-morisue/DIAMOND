/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;
import java.util.Set;

import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.SegDrawer;
import diamond.model.math.field.F;
import diamond.view.ui.screen.ScreenModel;

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

    public void draw(ScreenModel<T> screen, Graphics2D g2d) {
        SegDrawer.drawPointed(screen, g2d, pFar, qFar);
    }

    public Seg<T> clip(Set<Link<T>> links) {
        Ver<T> p = null;
        Ver<T> q = null;
        for (Link<T> link : links) {
            Ver<T> x = xPoint(link);
            if (x != null) {
                link.addNode(x);
                if (p == null) {
                    p = x;
                } else if (q == null) {
                    q = x;
                }
            }
        }
        return new Seg<T>(p, q, true, true);
    }

    private Ver<T> xPoint(D1<T> s) {
        if (isOn(s.p)) {
            return s.p;
        }
        if (isOn(s.q)) {
            return s.q;
        }
        Ver<T> x = s.nodes.xPoint(this);
        if (x != null) {
            return x;
        }
        Dir<T> d0 = s.dir();
        F<T> den = d0.prod(n);
        if (den.isZero()) {
            return null;
        }
        F<T> a = s.p.dir(p).prod(n).div(den);
        F<T> b = p.dir(s.q).prod(n).div(den);
        if (a.isNeg() && b.isNeg()) {
            return ((Dir<T>) d0.scale(a)).ver(s.q);
        }
        return null;
    }

}
