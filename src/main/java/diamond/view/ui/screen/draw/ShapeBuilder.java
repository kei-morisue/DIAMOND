/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

import diamond.model.cyborg.geom.Face;
import diamond.model.cyborg.geom.SegmentCrease;
import diamond.model.cyborg.geom.SegmentMV;
import diamond.model.cyborg.geom.Vertex;

/**
 * @author Kei Morisue
 *
 */
public class ShapeBuilder {
    public static Ellipse2D.Double build(Vertex v, double size) {
        double sizeHalf = size * 0.5;
        return new Ellipse2D.Double(
                v.getX() - sizeHalf,
                v.getY() - sizeHalf,
                size,
                size);
    }

    public static GeneralPath build(Face face) {
        GeneralPath outline = new GeneralPath();
        for (Vertex v : face.getVertices()) {
            outline.lineTo(v.getX(), v.getY());
        }
        outline.closePath();
        return outline;
    }

    public static Line2D.Double build(SegmentMV mV) {
        Vertex v0 = mV.getV0();
        Vertex v1 = mV.getV1();
        return build(v0, v1);
    }

    public static Line2D.Double build(SegmentCrease crease, double clip0,
            double clip1) {
        Vertex v0 = crease.getV0();
        Vertex v1 = crease.getV1();
        Vertex pivot = crease.getCenter();
        v0 = v0.scale(1.0 - clip0, pivot);
        v1 = v1.scale(1.0 - clip1, pivot);
        return build(v0, v1);
    }

    private static Line2D.Double build(Vertex v0, Vertex v1) {
        Line2D.Double line = new Line2D.Double(v0.getX(), v0.getY(), v1.getX(),
                v1.getY());
        return line;
    }

}
