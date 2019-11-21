/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg;

import java.awt.geom.Point2D;

/**
 * @author Kei Morisue
 *
 */
public class VertexProperty extends CyborgProperty {
    private Point2D.Double offset = new Point2D.Double();
    public boolean isFoldable = false;

    public Point2D.Double getOffset() {
        return this.offset;
    }

    public void setOffset(Point2D.Double offset) {
        this.offset = offset;
    }

}
