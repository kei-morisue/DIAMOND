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
public interface Mirror {
    // Mirror(Affine Transform) in 2D plane
    abstract Vertex apply(Vertex v0);

    abstract boolean isFlip();
}
