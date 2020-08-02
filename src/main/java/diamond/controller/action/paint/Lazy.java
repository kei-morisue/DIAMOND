/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.paint;

import java.awt.Graphics2D;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class Lazy implements PaintActionInterface {

    @Override
    public void doAction(Context context) {
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