/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

/**
 * @author Kei Morisue
 *
 */
public interface PaintActionInterface {

    public abstract void onLeftClick();

    public abstract void onLeftCtrlClick();

    public abstract void onRightClick();

    public abstract void onRightCtrlClick();

    public abstract void onMove();

    public abstract void onPress();

    public abstract void onDrag();

    public abstract void onRelease();

    public abstract void onDraw(Graphics2D g2d);
}