/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;
import java.util.Observable;

/**
 * @author Kei Morisue
 *
 */
public abstract class AbstractPaintAction extends Observable {

    public abstract void onLeftPress(boolean isCtrl);

    public abstract void onRightPress(boolean isCtrl);

    protected abstract void onLeftCtrl();

    protected abstract void onRightCtrl();

    public abstract void onMove();

    public void onPress(boolean isLeft, boolean isCtrl) {
        if (isLeft) {
            onLeftPress(isCtrl);
        } else {
            onRightPress(isCtrl);
        }
    };

    public abstract void onDrag();

    public abstract void onRelease();

    public abstract void onDraw(Graphics2D g2d);

    public abstract String getInfo();
}