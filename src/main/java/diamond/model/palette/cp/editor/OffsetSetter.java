/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.palette.cp.editor;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class OffsetSetter {

    public static void set(Cp cp, OriVertex v, Double offset) {
        Set<OriLine> lines = cp.getLines();
        for (OriLine l : lines) {
            if (DistanceUtil.distance(v, l.p0) < Constants.EPS) {
                l.p0.setOffset(offset);
            } else {
                if (DistanceUtil.distance(v,
                        l.p1) < Constants.EPS) {
                    l.p1.setOffset(offset);
                }
            }
        }
    }

    public static void set(Cp cp, Double p, Set<OriVertex> vertices) {
        for (OriVertex v : vertices) {
            if (v.isPickked()) {
                Double offset = Point2DUtil.scale(p, .1);
                v.setOffset(offset.x, offset.y);
                set(cp, v, offset);
            }
        }
    }

    public static void reset(Cp cp, OriVertex v) {
        Set<OriLine> lines = cp.getLines();
        for (OriLine l : lines) {
            if (DistanceUtil.distance(v, l.p0) < Constants.EPS) {
                l.p0.setOffset(.0, .0);
            } else {
                if (DistanceUtil.distance(v,
                        l.p1) < Constants.EPS) {
                    l.p1.setOffset(.0, .0);
                }
            }
        }
    }

    public static void reset(Set<OriVertex> vertices, Cp cp) {
        for (OriVertex v : vertices) {
            if (v.isPickked()) {
                v.setOffset(.0, .0);
                reset(cp, v);
            }
        }
    }

}
