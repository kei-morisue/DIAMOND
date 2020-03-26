/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractSegment {

    private Vertex v0;
    private Vertex v1;

    public AbstractSegment() {
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

    public Vertex scale(double p) {
        return getV1().sub(getV0()).scale(p).add(getV0());
    }

    public Vertex getCenter() {
        return scale(.5);
    }

}
