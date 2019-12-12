/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import diamond.Config;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeMirror {
    public static final double SCALE = Config.PAPER_SIZE * 5;

    public static Point2D.Double get(Point2D.Double p0, Vertex v,
            Point2D.Double p1) {
        Double d0 = Point2DUtil.sub(p0, v);
        Double d1 = Point2DUtil.sub(p1, v);
        double angle = Point2DUtil.angle(d1, d0);
        AffineTransform rot = AffineTransform.getRotateInstance(-angle);
        rot.transform(d1, d1);
        Double p2 = Point2DUtil.add(v,
                Point2DUtil.scale(Point2DUtil.normalize(d1), SCALE));
        Point2D.Double candidate = null;
        for (HalfEdge he : v.getHalfEdges()) {
            Face face = he.getFace();
            if (face == null) {
                continue;
            }
            ArrayList<HalfEdge> hes = CrossPointUtil.splitOutLines(face, v, p2);
            if (hes.size() == 2) {
                if (hes.get(0).getV0() == v) {
                    return hes.get(1).getV0();
                } else {
                    return hes.get(0).getV0();
                }
            } else if (hes.size() == 1) {
                candidate = hes.get(0).getV0();
            }
        }
        return candidate;
    }
}
