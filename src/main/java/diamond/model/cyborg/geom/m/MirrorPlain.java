/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.m;

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

    // R(s).(M.R(-s)(x - v0)) + v0
    // R(s).M.R(-s).x - R(s).M.R(-s).v0 + v0
    // A.x + B : Affine Transform
    // A := R(s).M.R(-s) = M.R(-2s) = M.R(t)
    // B := v0 - A.v0
    public MirrorPlain(SegmentBase segment) {
        this(segment.getV0(), segment.getV1());
    }

    public MirrorPlain(Vertex v0, Vertex v1) {
        setT(-2 * v1.dir(v0).angle());
        isFlip = true;
        this.b = v0.dir(applyA(v0).ver());
    }

}
