/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.awt.Graphics2D;

import diamond.model.cyborg.geom.Util;
import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.draw.LineDrawer;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;
import diamond.view.ui.screen.AbstractScreen;

/**
 * @author Kei Morisue
 *
 */
public final class Line<T extends F<T>> implements Graphic<T> {
    private Ver<T> p;
    private Dir<T> d;
    private Dir<T> n;
    private Ver<T> pFar;
    private Ver<T> qFar;
    private final static int EPS = 1000;

    @Deprecated
    public Line() {
    }

    public Line(Ver<T> p, Ver<T> q) {
        this.p = p;
        d = q.dir(p);
        n = d.n();
        Ver<T> c = d.div(2).ver(p);
        pFar = p.dir(c).mul(5).ver(c);
        qFar = q.dir(c).mul(5).ver(c);
    }

    public boolean isOn(Ver<T> v) {
        return p.dir(v).prod(n).isZero();
    }

    public Ver<T> xPoint(Seg<T> s0) {
        if (isOn(s0.p)) {
            return s0.p;
        }
        Ver<T> x = s0.nodes.xNode(this);
        if (x != null) {
            return x;
        }
        Dir<T> d0 = s0.dir();
        T den0 = d0.prod(n);
        if (den0.isZero()) {
            return null;
        }
        T a = s0.p.dir(p).prod(n).div(den0);
        T b = p.dir(s0.q).prod(n).div(den0);
        if (a.isNeg() && b.isNeg()) {
            return d0.scale(b).ver(s0.q);
        }
        return null;
    }

    @Override
    public <S extends AbstractScreen<T>> void draw(
            S screen,
            Graphics2D g2d,
            float scale,
            boolean isPointed) {
        LineDrawer.draw(screen, g2d, scale, pFar, qFar, isPointed);
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        return distSquare(x, y) < EPS / scale / scale;
    }

    @Override
    public double distSquare(double x, double y) {
        return Util.footSquare(pFar, qFar, x, y);
    }

    @Override
    public <S extends Graphic<T>> S find(Finder<T, S> finder, double x,
            double y, double scale) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

}
