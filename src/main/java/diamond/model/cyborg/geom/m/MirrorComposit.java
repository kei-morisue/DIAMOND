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
public class MirrorComposit extends Mirror {
    @Deprecated
    public MirrorComposit() {
    }

    // A1( A0.x + B0 ) + B1 = A1.A0.x + (A1.B0 + B1).
    public MirrorComposit(Mirror mirror1, Mirror mirror0) {
        this.b = mirror1.b;
        this.t = mirror1.t;
        compose(mirror0);
    }

    public MirrorComposit(SegmentBase segment, Mirror mirror0) {
        this(segment.getV0(), segment.getV1(), mirror0);
    }

    public MirrorComposit(Vertex v0, Vertex v1, Mirror mirror0) {
        super(v0, v1);
        compose(mirror0);
    }

    private void compose(Mirror mirror0) {
        flip = -mirror0.flip;
        setT(flip * 2 * t + mirror0.t);
        this.b = applyA(mirror0.b).add(b);
    }

    private Direction applyA(Direction d0) {
        double x0 = d0.getX();
        double y0 = d0.getY();
        double c = Math.cos(t * 2);
        double s = Math.sin(t * 2);
        return new Direction(
                x0 * c + y0 * s,
                (-x0 * s + y0 * c) * flip);
    }
}
