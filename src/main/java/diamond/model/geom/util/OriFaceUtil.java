/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class OriFaceUtil {
    public static boolean onFace(OriFace face, OriHalfEdge e) {
        OriVertex centerPoint = e.getSv().add(e.getEv()).scale(0.5);
        return onFace(face, centerPoint);
    }

    public static boolean onFace(OriFace face, OriVertex v) {
        double sumAngle = 0.0;
        for (OriHalfEdge he : face.getHalfEdges()) {
            Vector2d p0, p1;
            OriVertex sv = he.getSv();
            OriVertex ev = he.getEv();
            p0 = new Vector2d(sv.x - v.x, sv.y - v.y);
            p1 = new Vector2d(ev.x - v.x, ev.y - v.y);
            double le = p1.length();
            double ls = p0.length();
            if (le == 0.0 || ls == 0.0) {
                return true;
            }
            sumAngle += Math.acos((p0.x * p1.x + p0.y * p1.y) / ls / le);
        }
        return Math.abs(Math.sin(sumAngle)) < Constants.RADIAN_EPS &&
                Math.abs(Math.cos(sumAngle) - 1.0) < Constants.RADIAN_EPS;
    }

}
