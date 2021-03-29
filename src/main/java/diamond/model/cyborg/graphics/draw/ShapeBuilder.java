/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.model.cyborg.graphics.draw;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.List;

import diamond.model.cyborg.geom.d0.Ver;
import diamond.model.math.field.F;

/**
 * @author Kei Morisue
 *
 */
public class ShapeBuilder {
    public static <T extends F<T>> Ellipse2D.Double build(
            Ver<T> v,
            double size) {
        double sizeHalf = size * 0.5;
        return new Ellipse2D.Double(
                v.x.d() - sizeHalf,
                v.y.d() - sizeHalf,
                size,
                size);
    }

    public static <T extends F<T>> GeneralPath build(List<Ver<T>> vers) {
        GeneralPath outline = new GeneralPath();
        Ver<T> w = vers.get(vers.size() - 1);
        outline.moveTo(w.x.d(), w.y.d());
        for (Ver<T> v : vers) {
            outline.lineTo(v.x.d(), v.y.d());
        }
        outline.closePath();
        return outline;
    }

    //    public static GeneralPath build(Face face) {
    //        GeneralPath outline = new GeneralPath();
    //        LinkedList<Vertex> vertices = face.getVertices();
    //        Vertex v0 = vertices.get(0);
    //        outline.moveTo(v0.getX(), v0.getY());
    //        for (Vertex v : vertices) {
    //            outline.lineTo(v.getX(), v.getY());
    //        }
    //        outline.closePath();
    //        return outline;
    //    }
    //
    //    public static Line2D.Double build(SegmentEdge mV) {
    //        Vertex v0 = mV.getV0();
    //        Vertex v1 = mV.getV1();
    //        return build(v0, v1);
    //    }
    //
    //    public static Line2D.Double build(SegmentCrease crease, double clipped0,
    //            double clipped1) {
    //        Vertex v0 = crease.getV0();
    //        Vertex v1 = crease.getV1();
    //        Vertex pivot = v1.c(v0);
    //        v0 = v0.scale(clipped0, pivot);
    //        v1 = v1.scale(clipped1, pivot);
    //        return build(v0, v1);
    //    }
    //
    //    public static Line2D.Double build(SegmentBase segment) {
    //        Vertex v0 = segment.getV0();
    //        Vertex v1 = segment.getV1();
    //        return build(v0, v1);
    //    }
    //
    public static <T extends F<T>> Line2D.Double build(
            Ver<T> p,
            Ver<T> q) {
        return build(p.x.d(), p.y.d(), q.x.d(), q.y.d());
    }

    private static Line2D.Double build(
            double px,
            double py,
            double qx,
            double qy) {
        return new Line2D.Double(px, py, qx, qy);
    }
}
