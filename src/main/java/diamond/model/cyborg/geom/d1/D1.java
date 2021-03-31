/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.Util;
import diamond.model.cyborg.geom.d0.Dir;
import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.cyborg.graphics.Graphic;
import diamond.model.cyborg.graphics.find.Finder;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public abstract class D1<T extends F<T>> implements Graphic<T> {
    protected Ver<T> p;
    protected Ver<T> q;
    protected Nodes<T> nodes = new Nodes<T>();
    private static final double EPS = 100;

    @Deprecated
    protected D1() {
    }

    protected D1(Ver<T> p, Ver<T> q) {
        this.p = p;
        this.q = q;
    }

    public void add(Nodes<T> nodes) {
        this.nodes = nodes;
    }

    public Ver<T> c() {
        Dir<T> dir = q.dir(p);
        return dir.div(2).ver(p);
    }

    public void add(Ver<T> v) {
        F<T> dp = p.dir(v).norm();
        if (dp.isZero()) {
            return;
        }
        F<T> dq = q.dir(v).norm();
        if (dq.isZero()) {
            return;
        }
        F<T> d = dir().norm();
        if (dp.sub(d).isNeg() && dq.sub(d).isNeg()) {
            nodes.add(v);
        }
    }

    public boolean isNode(Ver<T> v) {
        return nodes.isNode(v);
    }

    public boolean has(Ver<T> v) {
        if (p == v) {
            return true;
        }
        if (q == v) {
            return true;
        }
        if (isNode(v)) {
            return true;
        }
        return false;
    }

    protected F<T> lengthSquared() {
        return q.dir(p).norm();
    }

    public <S extends Graphic<T>> S find(
            Finder<T, S> finder,
            double x,
            double y,
            double scale) {
        return finder.find(nodes, x, y, scale);
    }

    public boolean isdubbed(D1<T> s) {
        return s.p == p && s.q == q || s.p == q && s.q == p;
    }

    @Override
    public boolean isNear(double x, double y, double scale) {
        return distSquare(x, y) < EPS / scale / scale &&
                c().distSquare(x, y) < lengthSquared().div(4).d();
    }

    @Override
    public double distSquare(double x, double y) {
        return Util.footSquare(p, q, x, y);
    };

    public Dir<T> dir() {
        return q.dir(p);
    }

    public Dir<T> dir(D1<T> d) {
        return p.dir(d.p);
    }

    public Ver<T> findVer(D1<T> d0) {
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
        Ver<T> node = nodes.find(d0.nodes);
        if (node != null) {
            return node;
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

    @Deprecated
    public Nodes<T> getNodes() {
        return nodes;
    }

    @Deprecated
    public void setNodes(Nodes<T> nodes) {
        this.nodes = nodes;
    }
}
