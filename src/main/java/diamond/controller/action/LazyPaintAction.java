/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class LazyPaintAction implements PaintActionInterface {

    @Override
    public void doAction(Context context, Double clickedPoint) {
    }

    @Override
    public void undo(Context context) {
    }

    @Override
    public void destroy(Context context) {
    }

    @Override
    public void recover(Context context) {
    }

    @Override
    public PaintActionInterface onLeftClick(Context context) {
        return null;
    }

    @Override
    public PaintActionInterface onRightClick(Context context) {
        return null;
    }

    @Override
    public void onMove(Context context) {
    }

    @Override
    public void onPress(Context context) {
    }

    @Override
    public void onDrag(Context context) {
    }

    @Override
    public void onRelease(Context context) {
    }

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
    }

}
