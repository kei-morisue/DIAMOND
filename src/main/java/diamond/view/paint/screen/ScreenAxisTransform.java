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
public class ScreenAxisTransform {
    private AffineTransform allign = AffineTransform
            .getTranslateInstance(0, 0);
    private AffineTransform zoom = AffineTransform.getScaleInstance(1.0, 1.0);
    private AffineTransform translate = AffineTransform
            .getTranslateInstance(0.0, 0.0);
    private AffineTransform rotate = AffineTransform.getRotateInstance(0.0);

    private AffineTransform affineTransform = new AffineTransform();

    public ScreenAxisTransform(int width, int height) {
        allignCenter(width, height);
    }

    public AffineTransform getTransform() {
        affineTransform.setToIdentity();
        affineTransform.concatenate(allign);
        affineTransform.concatenate(zoom);
        affineTransform.concatenate(translate);
        affineTransform.concatenate(rotate);
        return affineTransform;
    }

    private void allignCenter(int width, int height) {
        allign.setToIdentity();
        allign.translate(width * 0.5, height * 0.5);
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

    public void Resize(int width, int height) {
        translate.setToIdentity();
        allignCenter(width, height);
    }

    public double getScale() {
        return this.zoom.getScaleX();
    }

}
