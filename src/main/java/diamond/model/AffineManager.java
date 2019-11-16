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
public class AffineManager {

    private static AffineTransform getZoom(AffineTransform transform) {
        double z = getScale(transform);
        return AffineTransform.getScaleInstance(z, z);
    }

    private static AffineTransform getRotate(AffineTransform transform) {
        double theta = getTheta(transform);
        return AffineTransform.getRotateInstance(theta);
    }

    private static AffineTransform getTranslate(AffineTransform transform) {
        double x = getX(transform);
        double y = getY(transform);
        return AffineTransform.getTranslateInstance(x, y);
    }

    public static double getTheta(AffineTransform transform) {
        return Math.atan2(transform.getScaleY(), transform.getScaleY());
    }

    public static double getScale(AffineTransform transform) {
        return Math.hypot(transform.getScaleX(), transform.getScaleY());
    }

    public static double getX(AffineTransform transform) {
        double theta = getTheta(transform);
        double x = transform.getTranslateX();
        double y = transform.getTranslateY();
        return x * Math.cos(theta) - y * Math.sin(theta);
    }

    public static double getY(AffineTransform transform) {
        double theta = getTheta(transform);
        double x = transform.getTranslateX();
        double y = transform.getTranslateY();
        return x * Math.sin(theta) + y * Math.cos(theta);
    }

    public static void zoom(AffineTransform transform, double scale) {
        transform.setToIdentity();
        AffineTransform zoom = getZoom(transform);
        zoom.concatenate(AffineTransform.getScaleInstance(scale, scale));
        transform.concatenate(getTranslate(transform));
        transform.concatenate(zoom);
        transform.concatenate(getRotate(transform));
    }

    public static void rotate(AffineTransform transform, double theta) {
        transform.setToIdentity();
        transform.concatenate(getZoom(transform));
        transform.concatenate(getTranslate(transform));
        AffineTransform rotate = getRotate(transform);
        rotate.concatenate(AffineTransform.getRotateInstance(theta));
        transform.concatenate(rotate);
    }

    public static void resize(AffineTransform transform, int w, int h) {
        transform.setToIdentity();
        transform.concatenate(getZoom(transform));
        AffineTransform translate = AffineTransform.getTranslateInstance(w >> 1,
                h >> 1);
        transform.concatenate(translate);
        transform.concatenate(getRotate(transform));
    }

    public static void translate(AffineTransform transform, double x,
            double y) {
        transform.setToIdentity();
        transform.concatenate(getZoom(transform));
        AffineTransform translate = getTranslate(transform);
        translate.concatenate(
                AffineTransform.getTranslateInstance(x, y));
        transform.concatenate(translate);
        transform.concatenate(getRotate(transform));
    }

}
