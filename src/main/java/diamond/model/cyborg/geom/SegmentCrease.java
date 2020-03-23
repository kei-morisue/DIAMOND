/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.geom;

/**
 * @author Kei Morisue
 *
 */
public final class SegmentCrease extends AbstractSegment {
    private Vertex v0;
    private Vertex v1;

    public SegmentCrease() {
    }

    public SegmentCrease(Vertex v0, Vertex v1) {
        this.v0 = v0;
        this.v1 = v1;
    }

    @Override
    Vertex getV0() {
        return v0;
    }

    @Override
    Vertex getV1() {
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

}
