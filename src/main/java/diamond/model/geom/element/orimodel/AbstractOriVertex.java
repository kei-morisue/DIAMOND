/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.orimodel;

import javax.vecmath.Vector2d;

/**
 * @author long_
 *
 */
public class AbstractOriVertex extends Vector2d {
    public AbstractOriVertex(double x, double y) {
        super(x, y);
    }

    public AbstractOriVertex(Vector2d v) {
        super(v);
    }

}
