/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.m;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.AbstractSegment;

/**
 * @author Kei Morisue
 *
 */
public class MirrorSimple implements Mirror {
    protected Direction b = new Direction(.0, .0);
    protected double t = .0;
    protected int flip = -1;
    private double c;
    private double s;

    @Deprecated
    public MirrorSimple() {
    }

    // Ax + B : Affine Transform
    // A := M.R
    // B := (I - M.A).v0
    public MirrorSimple(AbstractSegment segment) {
        this(segment.getV0(), segment.getV1());
    }

    public MirrorSimple(Vertex v0, Vertex v1) {
        setT(flip * v1.dir(v0).angle());
        setB(v0);
    }

    @Override
    public Vertex apply(Vertex v0) {
        return b.ver(applyA(v0));
    }

    @Override
    public boolean isFlip() {
        return flip == -1;
    }

    private void setB(Vertex v0) {
        double x = v0.getX();
        double y = v0.getY();
        this.b = new Direction(
                x * (1 - c) + y * s,
                flip * s * x + y * (1 - flip * c));
    }

    private Vertex applyA(Vertex v0) {
        double x0 = v0.getX();
        double y0 = v0.getY();
        return new Vertex(
                x0 * c + y0 * s,
                (-x0 * s + y0 * c) * flip);
    }

    @Deprecated
    public Direction getB() {
        return b;
    }

    @Deprecated
    public void setB(Direction b) {
        this.b = b;
    }

    @Deprecated
    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
        c = Math.cos(this.t);
        s = Math.sin(this.t);
    }

    @Deprecated
    public int getFlip() {
        return flip;
    }

    @Deprecated
    public void setFlip(int flip) {
        this.flip = flip;
    }

}
