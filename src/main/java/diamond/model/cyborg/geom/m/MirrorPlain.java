/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.m;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentBase;

/**
 * @author Kei Morisue
 *
 */
public class MirrorPlain extends AbstractMirror {
    @Deprecated
    public MirrorPlain() {
        super();
    }

    // Ax + B : Affine Transform
    // A := R(s).M.R(-s) = M.R(-2s) = M.R(t)
    // B := (I - A).v0
    public MirrorPlain(SegmentBase segment) {
        this(segment.getV0(), segment.getV1());
    }

    public MirrorPlain(Vertex v0, Vertex v1) {
        setT(-2 * v1.dir(v0).angle());
        setB(v0);
        isFlip = true;
    }

    private void setB(Vertex v0) {
        double x = v0.getX();
        double y = v0.getY();
        this.b = new Direction(
                x * (1 - c) + y * s,
                -pm() * s * x + y * (1 - pm() * c));
    }

}
