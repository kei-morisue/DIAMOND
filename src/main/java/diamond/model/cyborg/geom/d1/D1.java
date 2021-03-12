/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.Metric;
import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.Util;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class D1<T extends F<T>> implements Metric {
    protected Ver<T> p;
    protected Ver<T> q;
    private static final double EPS = 50;

    @Deprecated
    protected D1() {
    }

    protected D1(Ver<T> p, Ver<T> q) {
        this.p = p;
        this.q = q;
    }

    public Ver<T> c() {
        Dir<T> dir = q.dir(p);
        return dir.div(2).ver(p);
    }

    private F<T> lengthSquared() {
        return q.dir(p).norm();
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        double d = EPS / scale / scale;
        return Util.footSquare(p, q, x, y) < d &&
                c().isNear(x, y, scale / lengthSquared().d());
    }

    @Deprecated
    public Ver<T> getP() {
        return p;
    }

    @Deprecated
    public void setP(Ver<T> p) {
        this.p = p;
    }

    @Deprecated
    public Ver<T> getQ() {
        return q;
    }

    @Deprecated
    public void setQ(Ver<T> q) {
        this.q = q;
    }
}
