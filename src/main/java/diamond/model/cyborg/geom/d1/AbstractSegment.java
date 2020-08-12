/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom.d1;

import diamond.model.cyborg.geom.Cyborg;
import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.graphics.GraphicsCp;
import diamond.model.math.Fuzzy;
import diamond.model.math.Util;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractSegment implements Cyborg, GraphicsCp {
    protected Vertex v0;
    protected Vertex v1;
    protected SegmentType type = SegmentType.CREASE;

    @Deprecated
    protected AbstractSegment() {
    }

    public AbstractSegment(Vertex v0, Vertex v1) {
        this.v0 = v0;
        this.v1 = v1;
    }

    public abstract void split(Vertex v);

    @Override
    public double dist(Vertex v) {
        return v.dist(c());
    }

    public Vertex[] split(AbstractSegment segment) {
        Direction d0 = dir();
        Direction d1 = segment.dir();
        double det = d0.outer(d1);
        if (Fuzzy.isSmall(det)) {
            return null;
        }
        Direction d = v0.dir(segment.getV0()).scale(-1.0 / det);
        double p0 = d1.n().prod(d);
        double p1 = d0.n().prod(d);
        if (Util.in(p0, .0, 1.0) && Util.in(p1, .0, 1.0)) {
            Vertex[] vs = { split(p0), segment.split(p1) };
            return vs;
        }
        return null;
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
        return Fuzzy.in(p, .0, 1.0) && Fuzzy.isSmall(h(v));
    }

    public double angle() {
        return getV1().angle(getV0());
    }

    public double h(Vertex v) {
        Direction a = v0.dir(v);
        Direction b = v1.dir(v);
        Direction c = v1.dir(v0);
        return a.outer(b) / c.norm();
    }

    public Vertex c() {
        return dir().scale(.5).ver(v0);
    }

    public Direction dir() {
        return v1.dir(v0);
    }

    public Vertex split(Double p) {
        if (Fuzzy.isSmall(p - 1.0)) {
            return v1;
        }
        if (Fuzzy.isSmall(p - .0)) {
            return v0;
        }
        return v1.scale(p, v0);
    }

    public Vertex getV0() {
        return v0;
    }

    public Vertex getV1() {
        return v1;
    }

    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        if (!SegmentType.isCrease(type)) {
            return;
        }
        this.type = type;
    }

    @Deprecated
    public void setV0(Vertex v0) {
        this.v0 = v0;
    }

    @Deprecated
    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

}
