/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen;

import java.awt.geom.AffineTransform;

/**
 * @author long_
 *
 */
public class ScreenTransform {
    private AffineTransform focus = AffineTransform
            .getTranslateInstance(0, 0);
    private AffineTransform zoom = AffineTransform.getScaleInstance(1.0, 1.0);
    private AffineTransform translate = AffineTransform
            .getTranslateInstance(0.0, 0.0);
    private AffineTransform rotate = AffineTransform.getRotateInstance(0.0);

    private AffineTransform affineTransform = new AffineTransform();

    public ScreenTransform(int width, int height) {
        focus(width, height);
    }

    public ScreenTransform(ScreenTransform transform) {
        this.focus = new AffineTransform(transform.translate);
        this.zoom = new AffineTransform(transform.zoom);
        this.translate = new AffineTransform(transform.translate);
        this.rotate = new AffineTransform(transform.rotate);
    }

    public AffineTransform getTransform() {
        affineTransform.setToIdentity();
        affineTransform.concatenate(focus);
        affineTransform.concatenate(zoom);
        affineTransform.concatenate(translate);
        affineTransform.concatenate(rotate);
        return affineTransform;
    }

    private void focus(int width, int height) {
        focus.setToIdentity();
        focus.translate(width * 0.5, height * 0.5);
    }

    public void translate(double x, double y) {
        translate.translate(x, y);
    }

    public void zoom(double scale) {
        zoom.scale(scale, scale);
    }

    public void rotate(double radian) {
        rotate.rotate(radian);
    }

    public void ResizeWindow(int width, int height) {
        focus(width, height);
    }

    public double getScale() {
        return this.zoom.getScaleX();
    }

}
