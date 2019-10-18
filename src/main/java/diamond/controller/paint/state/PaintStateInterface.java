/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;

/**
 * @author long_
 *
 */
public interface PaintStateInterface {
    public PaintStateInterface doAction(Context context,
            Point2D.Double currentPoint);

    public PaintStateInterface undo(Context context);

    public void setNextState(PaintStateInterface state);

    public void setPreviousState(PaintStateInterface state);

    public PaintStateInterface getNextState();

    public PaintStateInterface getPreviousState();
}
