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

    abstract Vertex getV0();

    abstract Vertex getV1();

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
