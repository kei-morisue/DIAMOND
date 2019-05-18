/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.util;

import java.awt.geom.Point2D.Double;
import java.util.Collection;

import javax.vecmath.Vector2d;

import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriPoint;
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

    public static boolean onFace(OriFace face, Double v) {
        double sumAngle = 0.0;
        for (OriHalfEdge he : face.getHalfEdges()) {
            Vector2d p0, p1;
            OriVertex sv = he.getSv();
            OriVertex ev = he.getEv();
            p0 = new Vector2d(sv.x - v.x, sv.y - v.y);
            p1 = new Vector2d(ev.x - v.x, ev.y - v.y);
            double le = p1.length();
            double ls = p0.length();
            if (le < Constants.EPS || ls < Constants.EPS) {
                return true;
            }
            sumAngle += Math.acos((p0.x * p1.x + p0.y * p1.y) / ls / le);
        }
        return Math.abs(Math.sin(sumAngle)) < Constants.RADIAN_EPS &&
                Math.abs(Math.cos(sumAngle) - 1.0) < Constants.RADIAN_EPS;
    }

    public static OriVertex getCenterPoint(OriFace oriFace) {
        Collection<OriHalfEdge> halfEdges = oriFace.getHalfEdges();
        OriVertex centerP = new OriVertex();
        for (OriHalfEdge he : halfEdges) {
            centerP = centerP.add(he.getSv());
        }
        centerP = centerP.scale(1.0 / halfEdges.size());
        return centerP;
    }

    public static boolean crossFace(OriFace face, OriHalfEdge he0) {
        Double p0 = he0.getSv().getFoldedPosition();
        Double p1 = he0.getEv().getFoldedPosition();
        OriPoint cp0 = null;
        OriPoint cp1 = null;
        for (OriHalfEdge he1 : face.getHalfEdges()) {
            Double q0 = he1.getSv().getFoldedPosition();
            Double q1 = he1.getEv().getFoldedPosition();
            if (cp0 == null) {
                cp0 = CrossPointUtil.getCrossPoint(p0, p1, q0, q1);
            }
            if (cp0 != null && cp1 == null) {
                cp1 = CrossPointUtil.getCrossPoint(p0, p1, q0, q1);
                if (cp1 != null) {
                    if (cp0.distance(cp1) < Constants.EPS) {
                        cp1 = null;
                    }
                }
            }
        }
        if (cp0 != null && cp1 != null) {
            if (!isSame(p0, p1, cp0) || !isSame(p0, p1, cp1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSame(Double p0, Double p1, OriPoint cp) {
        return cp.distance(p0) < Constants.EPS
                || cp.distance(p1) < Constants.EPS;
    }
}
