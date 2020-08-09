/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.geom.d2;

import diamond.model.cyborg.geom.d0.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class MirrorLazy implements Mirror {
    @Deprecated
    public MirrorLazy() {
    }

    @Override
    public Vertex apply(Vertex v0) {
        return v0;
    }

    @Override
    public boolean isFront() {
        return true;
    }

}
