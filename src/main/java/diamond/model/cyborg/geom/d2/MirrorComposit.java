/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d0.Direction;
import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class MirrorComposit extends MirrorSimple {
    @Deprecated
    public MirrorComposit() {
    }

    public MirrorComposit(Vertex v0, Vertex v1, MirrorSimple mirror) {
        super(v0, v1);
        flip = -mirror.flip;
        setT(flip * 2 * t + mirror.t);
        this.b = applyA(mirror.b).add(b);
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
