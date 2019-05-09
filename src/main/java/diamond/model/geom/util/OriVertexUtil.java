/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class OriVertexUtil {
    public static OriVertex getCenterPoint(OriVertex v0, OriVertex v1) {
        return v0.add(v1).scale(0.5);
    }

    public static OriVertex getScaledPoint(
            double scale,
            OriVertex centerP,
            OriVertex v) {
        return new OriVertex(
                v.x * scale + centerP.x * (1.0 - scale),
                v.y * scale + centerP.y * (1.0 - scale));
    }
}
