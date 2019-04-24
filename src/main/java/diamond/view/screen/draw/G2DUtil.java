/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * @author long_
 *
 */
public class G2DUtil {

    public static double getScale(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        return Math.hypot(transform.getScaleX(), transform.getShearX());
    }

    public static double getTheta(Graphics2D g2d) {
        AffineTransform transform = g2d.getTransform();
        double x = transform.getScaleX();
        double y = -transform.getShearX();
        return Math.atan2(y, x);
    }

}
