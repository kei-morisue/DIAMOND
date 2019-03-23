/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.origami;

import java.awt.geom.Point2D;

/**
 * @author long_
 *
 */
public class AbstractOriVertex extends Point2D.Double {
    public AbstractOriVertex(double x, double y) {
        super(x, y);
    }

    public AbstractOriVertex(Point2D.Double v) {
        super(v.x, v.y);
    }

    @Deprecated
    public void setX(double x) {
        this.x = x;
    }

    @Deprecated
    public void setY(double y) {
        this.y = y;
    }

}
