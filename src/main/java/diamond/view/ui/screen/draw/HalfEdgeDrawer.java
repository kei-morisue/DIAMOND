/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.view.ui.screen.style.HalfEdgeStyle;

/**
 * @author Kei Morisue
 *
 */
public class HalfEdgeDrawer {
    private static Shape buildLine(double x0, double y0, double x1, double y1) {
        return new Line2D.Double(x0, y0, x1, y1);
    }

    private static Shape buildLine(Point2D.Double v0, Point2D.Double v1) {
        return buildLine(v0.x, v0.y, v1.x, v1.y);
    }

    public static Shape buildLine(HalfEdge he) {
        return new Line2D.Double(he.getV0(), he.getV1());
    }

    public static Shape buildLine(HalfEdge he, double scale) {
        Vertex v0 = he.getV0();
        Vertex v1 = he.getV1();
        Double pivot = CenterPointUtil.get(v0, v1);
        Double p0 = new Double(v0.x, v0.y);
        Double p1 = new Double(v1.x, v1.y);
        if (v0.isVertex()) {
            p0 = Point2DUtil.scale(v0, pivot, HalfEdgeStyle.CLIP_SCALE);
        }
        if (v1.isVertex()) {
            p1 = Point2DUtil.scale(v1, pivot, HalfEdgeStyle.CLIP_SCALE);
        }
        return buildLine(p0, p1);
    }

    public static Shape buildFoldedLine(HalfEdge he) {
        return new Line2D.Double(he.getV0().getFoldedOffset(),
                he.getV1().getFoldedOffset());
    }

    public static Shape buildFoldedLine(HalfEdge he, double scale) {
        Double v0 = he.getV0().getFoldedOffset();
        Double v1 = he.getV1().getFoldedOffset();
        Double pivot = CenterPointUtil.get(v0, v1);
        Double p0 = new Double(v0.x, v0.y);
        Double p1 = new Double(v1.x, v1.y);
        if (he.getV0().isVertex()) {
            p0 = Point2DUtil.scale(v0, pivot, HalfEdgeStyle.CLIP_SCALE);
        }
        if (he.getV1().isVertex()) {
            p1 = Point2DUtil.scale(v1, pivot, HalfEdgeStyle.CLIP_SCALE);
        }
        return buildLine(p0, p1);
    }
}
