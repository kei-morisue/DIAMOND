/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.math.Fuzzy;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractSegment {
    private Vertex v0;
    private Vertex v1;

    @Deprecated
    protected AbstractSegment() {
    }

    public AbstractSegment(Vertex v0, Vertex v1) {
        this.v0 = v0;
        this.v1 = v1;
    }

    public Vertex getV0() {
        return v0;
    }

    public Vertex getV1() {
        return v1;
    }

    @Deprecated
    public void setV0(Vertex v0) {
        this.v0 = v0;
    }

    @Deprecated
    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public double angle() {
        return getV1().angle(getV0());
    }

    public double dist(Vertex v) {
        Direction a = v0.dir(v);
        Direction b = v1.dir(v);
        Direction c = v1.dir(v0);
        return a.outer(b) / c.norm();
    }

    public Vertex foot(Vertex v) {
        Direction a = v.dir(v0);
        Direction b = v1.dir(v0);
        return b.scale(a.proj(b)).ver(v0);
    }

    public boolean isOn(Vertex v) {
        Direction a = v.dir(v0);
        Direction b = v1.dir(v0);
        double p = a.proj(b);
        return Fuzzy.in(p, 0, 1) && Fuzzy.isSmall(dist(v));
    }

}
