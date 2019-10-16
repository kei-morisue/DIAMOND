/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D;

import diamond.controller.paint.context.PaintContext;

/**
 * @author long_
 *
 */
public interface PaintStateInterface {
    public PaintStateInterface doAction(
            PaintContext context,
            Point2D.Double currentPoint);

    public PaintStateInterface undo(PaintContext paintContext);

    public void setNextState(PaintStateInterface state);

    public void setPreviousState(PaintStateInterface state);

    public PaintStateInterface getNextState();

    public PaintStateInterface getPreviousState();
}
