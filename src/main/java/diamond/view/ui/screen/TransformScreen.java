/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen;

import java.awt.geom.AffineTransform;

/**
 * @author Kei Morisue
 *
 */
public class TransformScreen {
    private double focusX = .0;
    private double focusY = .0;
    private double zoom = 1.0;
    private double shiftX = .0;
    private double shiftY = .0;
    private double rotate = .0;

    public TransformScreen() {
        focus(0, 0);
    }

    public TransformScreen(int width, int height) {
        focus(width, height);
    }

    public TransformScreen(TransformScreen transform) {
        this.focusX = transform.focusX;
        this.focusY = transform.focusY;
        this.zoom = transform.zoom;
        this.shiftX = transform.shiftX;
        this.shiftY = transform.shiftY;
        this.rotate = transform.rotate;
    }

    public AffineTransform getTransform() {
        AffineTransform t = new AffineTransform();
        t.concatenate(AffineTransform.getTranslateInstance(focusX, focusY));
        t.concatenate(AffineTransform.getScaleInstance(zoom, zoom));
        t.concatenate(AffineTransform.getTranslateInstance(shiftX, shiftY));
        t.concatenate(AffineTransform.getRotateInstance(rotate));
        return t;
    }

    public AffineTransform getInverse() {
        AffineTransform t = new AffineTransform();
        t.concatenate(AffineTransform.getRotateInstance(-rotate));
        t.concatenate(AffineTransform.getTranslateInstance(-shiftX, -shiftY));
        t.concatenate(AffineTransform.getScaleInstance(1.0 / zoom, 1.0 / zoom));
        t.concatenate(AffineTransform.getTranslateInstance(-focusX, -focusY));
        return t;
    }

    public void focus(int width, int height) {
        focusX = width >> 1;
        focusY = height >> 1;
    }

    public void shift(double x, double y) {
        shiftX += x;
        shiftY += y;
    }

    public void zoom(double scale) {
        zoom *= scale;
    }

    public void rotate(double radian) {
        rotate += radian;
    }

    public void resize(int width, int height) {
        focus(width, height);
    }

    @Deprecated
    public double getFocusX() {
        return focusX;
    }

    @Deprecated
    public void setFocusX(double focusX) {
        this.focusX = focusX;
    }

    @Deprecated
    public double getFocusY() {
        return focusY;
    }

    @Deprecated
    public void setFocusY(double focusY) {
        this.focusY = focusY;
    }

    public double getZoom() {
        return zoom;
    }

    @Deprecated
    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    @Deprecated
    public double getShiftX() {
        return shiftX;
    }

    @Deprecated
    public void setShiftX(double shiftX) {
        this.shiftX = shiftX;
    }

    @Deprecated
    public double getShiftY() {
        return shiftY;
    }

    @Deprecated
    public void setShiftY(double shiftY) {
        this.shiftY = shiftY;
    }

    public double getRotate() {
        return rotate;
    }

    @Deprecated
    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

}
