/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.vecmath.Vector2d;

import diamond.controller.paint.PaintContext;

/**
 * @author long_
 *
 */
public interface PaintActionInterface {

    public abstract void doAction(
            PaintContext context,
            Point2D.Double clickedPoint);

    public abstract void undo(PaintContext context);

    public abstract void destroy(PaintContext context);

    public abstract void recover(PaintContext context);

    public abstract PaintActionInterface onLeftClick(PaintContext context);

    public abstract PaintActionInterface onRightClick(PaintContext context);

    public abstract Vector2d onMove(PaintContext context);

    public abstract void onPress(PaintContext context);

    public abstract void onDrag(PaintContext context);

    public abstract void onRelease(PaintContext context);

    public abstract void onDraw(Graphics2D g2d, PaintContext context);

}
