/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.geom.Point2D;
import java.util.List;

import diamond.Config;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class FaceUtil {
    public static boolean onFace(Face face, HalfEdge he) {
        return onFace(face, CenterPointUtil.get(he));
    }

    public static boolean onFace(Face face, Point2D.Double v) {
        double sumAngle = 0.0;
        for (HalfEdge he : face.getHalfEdges()) {
            Vertex v0 = he.getV0();
            Vertex v1 = he.getV1();
            Point2D.Double p0 = Point2DUtil.sub(v0, v);
            Point2D.Double p1 = Point2DUtil.sub(v1, v);
            double l1 = p1.distance(.0, .0);
            double l0 = p0.distance(.0, .0);
            if (l0 < Config.EPSILON || l1 < Config.EPSILON) {
                return false;
            }
            sumAngle += Math.acos(Point2DUtil.prod(p0, p1) / l0 / l1);
        }
        return Math.abs(Math.sin(sumAngle)) < Config.EPSILON_RADIAN &&
                Math.abs(Math.cos(sumAngle) - 1.0) < Config.EPSILON_RADIAN;
    }

    public static void insert(List<Face> faces, Face f0, Face f1) {
        faces.remove(f0);
        int i1 = faces.indexOf(f1);
        faces.add(i1, f0);
    }

    public static void lift(List<Face> faces, Face f0, Face f1) {
        faces.remove(f0);
        int i1 = faces.indexOf(f1);
        if (faces.size() == i1 + 1) {
            faces.add(f0);
        } else {
            faces.add(i1 + 1, f0);
        }
    }

}
