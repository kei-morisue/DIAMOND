/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.m;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class MirrorLazy extends Mirror {
    public MirrorLazy() {
        super();
        this.flip = 1;
    }

    // Identity Operator
    @Override
    public Vertex apply(Vertex v0) {
        return v0;
    }

    @Override
    public boolean isFlip() {
        return false;
    }

}
