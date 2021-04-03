/**
 * DEFOX - Diagram Editor for Origami Creators
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import java.util.Comparator;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class NodeComparator<T extends F<T>> implements Comparator<Ver<T>> {
    private Ver<T> p;
    private Ver<T> q;

    @Deprecated
    public NodeComparator() {
    }

    public NodeComparator(Ver<T> p, Ver<T> q) {
        this.p = p;
        this.q = q;
    }

    @Override
    public int compare(Ver<T> v1, Ver<T> v2) {
        T n1 = p.dir(v1).norm();
        T n2 = p.dir(v2).norm();
        T m1 = q.dir(v1).norm();
        T m2 = q.dir(v2).norm();
        T dp = n1.sub(n2);
        T dq = m2.sub(m1);

        if (dp.isNeg() && dq.isNeg()) {
            return -1;
        }
        if (!dp.isNeg() && !dq.isNeg()) {
            return 1;
        }
        return 0;
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