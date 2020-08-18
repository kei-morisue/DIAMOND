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
public class MirrorLazy extends AbstractMirror {
    public MirrorLazy() {
        super();
    }

    // Identity Operator
    @Override
    public Vertex apply(Vertex v0) {
        return new Vertex(v0.getX(), v0.getY());
    }

    @Override
    public boolean isFlip() {
        return false;
    }

}
