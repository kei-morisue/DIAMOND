/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.controller.paint.context.Context;

/**
 * @author long_
 *
 */
public interface PaintActionInterface {

    public abstract void doAction(
            Context context,
            Point2D.Double clickedPoint);

    public abstract void undo(Context context);

    public abstract void destroy(Context context);

    public abstract void recover(Context context);

    public abstract PaintActionInterface onLeftClick(Context context);

    public abstract PaintActionInterface onRightClick(Context context);

    public abstract Point2D.Double onMove(Context context);

    public abstract void onPress(Context context);

    public abstract void onDrag(Context context);

    public abstract void onRelease(Context context);

    public abstract void onDraw(Graphics2D g2d, Context context);

}
