/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint;

import java.awt.geom.Point2D;

import diamond.model.geom.OriPoint;

/**
 * @author long_
 *
 */
public class PaintContext extends AbstractContext {
    public Point2D.Double currentlogicalMousePoint;
    public Point2D clickedLatestPoint;
    public boolean bEffective = true;
    public OriPoint pointedOriPoint = new OriPoint(0.0, 0.0);

}
