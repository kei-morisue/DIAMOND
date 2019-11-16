/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model;

import java.awt.geom.AffineTransform;

/**
 * @author Kei Morisue
 *
 */
public class ScreenTransform extends AffineTransform {
    private AffineTransform focus = AffineTransform
            .getTranslateInstance(0.0, 0.0);
    private AffineTransform zoom = AffineTransform.getScaleInstance(1.0, 1.0);
    private AffineTransform translate = AffineTransform
            .getTranslateInstance(0.0, 0.0);
    private AffineTransform rotate = AffineTransform.getRotateInstance(0.0);

    private AffineTransform affineTransform = new AffineTransform();

    public ScreenTransform() {
        focus(0, 0);
    }

    public ScreenTransform(int width, int height) {
        focus(width, height);
    }

    public ScreenTransform(ScreenTransform transform) {
        this.focus = new AffineTransform(transform.focus);
        this.zoom = new AffineTransform(transform.zoom);
        this.translate = new AffineTransform(transform.translate);
        this.rotate = new AffineTransform(transform.rotate);
    }

    public void concat() {
        setToIdentity();
        concatenate(focus);
        concatenate(zoom);
        concatenate(translate);
        concatenate(rotate);
    }

    private void focus(int width, int height) {
        focus.setToIdentity();
        focus.translate(width >> 1, height >> 1);
        concat();
    }

    public void translate(double x, double y) {
        translate.translate(x, y);
        concat();
    }

    public void zoom(double scale) {
        zoom.scale(scale, scale);
        concat();
    }

    public void rotate(double radian) {
        rotate.rotate(radian);
        concat();
    }

    public void resize(int width, int height) {
        focus(width, height);
        concat();
    }

    public double getScale() {
        return this.zoom.getScaleX();
    }

    public double getTheta() {
        return Math.atan2(rotate.getShearY(), rotate.getScaleX());
    }

    @Deprecated

    public AffineTransform getFocus() {
        return this.focus;
    }

    @Deprecated
    public void setFocus(AffineTransform focus) {
        this.focus = focus;
    }

    @Deprecated
    public AffineTransform getZoom() {
        return this.zoom;
    }

    @Deprecated
    public void setZoom(AffineTransform zoom) {
        this.zoom = zoom;
    }

    @Deprecated
    public AffineTransform getTranslate() {
        return this.translate;
    }

    @Deprecated
    public void setTranslate(AffineTransform translate) {
        this.translate = translate;
    }

    @Deprecated
    public AffineTransform getRotate() {
        return this.rotate;
    }

    @Deprecated
    public void setRotate(AffineTransform rotate) {
        this.rotate = rotate;
    }

    @Deprecated
    public AffineTransform getAffineTransform() {
        return this.affineTransform;
    }

    @Deprecated
    public void setAffineTransform(AffineTransform affineTransform) {
        this.affineTransform = affineTransform;
    }
}
