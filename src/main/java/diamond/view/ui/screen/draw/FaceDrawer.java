/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.CenterPointUtil;
import diamond.model.cyborg.util.Point2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class FaceDrawer {
    public static GeneralPath buildOutline(Face face, double scale) {
        Point2D.Double pivot = CenterPointUtil.get(face);
        GeneralPath outline = null;
        for (HalfEdge he : face.getHalfEdges()) {
            Vertex v0 = he.getV0();
            Point2D.Double scaledPoint = Point2DUtil.scale(v0, pivot, scale);
            double x = scaledPoint.getX();
            double y = scaledPoint.getY();
            if (outline == null) {
                outline = new GeneralPath();
                outline.moveTo(x, y);
            } else {
                outline.lineTo(x, y);
            }
        }
        outline.closePath();
        return outline;
    }

    public static GeneralPath buildFoldedOutline(Face face) {
        GeneralPath outline = null;
        ArrayList<HalfEdge> halfEdges = face.getHalfEdges();
        for (HalfEdge he : halfEdges) {
            Vertex v0 = he.getV0();
            Point2D p = Point2DUtil.add(
                    v0.getFolded(),
                    v0.getOffset());
            double x = p.getX();
            double y = p.getY();
            if (outline == null) {
                outline = new GeneralPath();
                outline.moveTo(x, y);
            } else {
                outline.lineTo(x, y);
            }
        }
        outline.closePath();
        return outline;
    }
}
