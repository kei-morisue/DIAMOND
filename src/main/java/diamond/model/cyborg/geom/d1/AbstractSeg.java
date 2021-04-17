/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.Util;
import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractSeg<T extends F<T>> implements Graphic<T> {
    protected Ver<T> p;
    protected Ver<T> q;
    private static final double EPS = 100;

    @Deprecated
    protected AbstractSeg() {
    }

    protected AbstractSeg(Ver<T> p, Ver<T> q) {
        this.p = p;
        this.q = q;
    }

    public Ver<T> c() {
        Dir<T> dir = q.dir(p);
        return dir.div(2).ver(p);
    }

    public boolean isdubbed(AbstractSeg<T> s) {
        return s.p == p && s.q == q || s.p == q && s.q == p;
    }

    public Dir<T> dir() {
        return q.dir(p);
    }

    public Dir<T> dir(AbstractSeg<T> d) {
        return p.dir(d.p);
    }

    public Ver<T> findPQ(AbstractSeg<T> d0) {
        if (d0.p == p) {
            return p;
        }
        if (d0.p == q) {
            return q;
        }
        if (d0.q == p) {
            return p;
        }
        if (d0.q == q) {
            return q;
        }
        return null;
    }

    public Dir<T> dir(Ver<T> v) {
        if (v == p) {
            return q.dir(p);
        }
        if (v == q) {
            return p.dir(q);
        }
        return p.dir(v);
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        return distSquare(x, y) < EPS / scale / scale &&
                c().distSquare(x, y) < q.dir(p).norm().div(4).d();
    }

    @Override
    public double distSquare(double x, double y) {
        return Util.footSquare(p, q, x, y);
    };

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

    @Override
    public String toString() {
        return p.toString() + "\n" + q.toString();
    }

}
