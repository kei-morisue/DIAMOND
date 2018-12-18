/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.paint.util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D.Double;

import javax.vecmath.Vector2d;

import diamond.paint.EditMode;
import diamond.paint.GraphicMouseActionInterface;
import diamond.paint.core.PaintContext;

/**
 * @author long_
 *
 */
public class LazyMouseAction implements GraphicMouseActionInterface {

    @Override
    public void destroy(PaintContext context) {

    }

    @Override
    public void doAction(PaintContext context, Double point,
            boolean differntAction) {

    }

    @Override
    public EditMode getEditMode() {
        return EditMode.INPUT;
    }

    @Override
    public boolean needSelect() {
        return false;
    }

    @Override
    public void onDrag(PaintContext context, AffineTransform affine,
            boolean differentAction) {
    }

    @Override
    public void onDraw(Graphics2D g2d, PaintContext context) {
    }

    @Override
    public GraphicMouseActionInterface onLeftClick(PaintContext context,
            AffineTransform affine, boolean differentAction) {
        return null;
    }

    @Override
    public Vector2d onMove(PaintContext context, AffineTransform affine,
            boolean differentAction) {
        return null;
    }

    @Override
    public void onPress(PaintContext context, AffineTransform affine,
            boolean differentAction) {
    }

    @Override
    public void onRelease(PaintContext context, AffineTransform affine,
            boolean differentAction) {
    }

    @Override
    public void onRightClick(PaintContext context, AffineTransform affine,
            boolean differentAction) {
    }

    @Override
    public void recover(PaintContext context) {

    }

    @Override
    public void undo(PaintContext context) {
    }

}
