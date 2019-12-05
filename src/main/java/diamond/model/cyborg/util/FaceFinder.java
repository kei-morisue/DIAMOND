/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;

import diamond.Config;
import diamond.controller.Context;
import diamond.model.cyborg.Face;

/**
 * @author Kei Morisue
 *
 */
public class FaceFinder {
    public static Face find(Context context) {
        double min = Double.MAX_VALUE;
        Face candidate = null;
        Point2D.Double p = context.getMousePoint();
        for (Face face : context.getCp().getFaces()) {
            double dist = CenterPointUtil.get(face).distance(p);
            if (dist < min) {
                min = dist;
                candidate = face;
            }
        }
        double scale = context.getPaintScreen().getTransform().getScale();
        if (min < Config.EPSILON_FACE / scale) {
            return candidate;
        } else {
            return null;
        }
    }
}
