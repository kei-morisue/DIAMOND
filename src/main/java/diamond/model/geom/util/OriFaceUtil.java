/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriVertex;

/**
 * @author long_
 *
 */
public class OriFaceUtil {
    public static boolean onFace(OriFace face, OriHalfEdge e) {
        Vector2d centerPoint = new Vector2d();
        centerPoint.add(e.getEv());
        centerPoint.add(e.getSv());
        centerPoint.scale(0.5);
        return onFace(face, centerPoint);
    }

    public static boolean onFace(OriFace face, Vector2d v) {
        double sumAngle = 0.0;
        for (OriHalfEdge he : face.getHalfEdges()) {
            OriVertex sv = he.getSv();
            sv.sub(v);
            OriVertex ev = he.getEv();
            ev.sub(v);
            double le = ev.length();
            double ls = sv.length();
            if (le == 0.0 || ls == 0.0) {
                return true;
            }
            sumAngle += Math.acos((sv.x * ev.x + sv.y * ev.y) / ls / le);
        }
        return Math.abs((sumAngle) % (Math.PI * 2)) < Constants.EPS;
    }

}
