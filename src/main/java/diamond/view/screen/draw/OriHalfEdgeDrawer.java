/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D.Double;

import diamond.model.geom.element.LineType;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class OriHalfEdgeDrawer {

    public static void drawFoldedCreaseLine(
            Graphics2D g2d,
            OriHalfEdge he,
            Color color,
            Stroke stroke,
            double clipScale) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        OriVertex s = he.getSv();
        OriVertex e = he.getEv();

        Double ev = e.getFoldedPosition();
        Double sv = s.getFoldedPosition();
        sv = Point2DUtil.plus(sv, s.getRotatedOffset(g2d));
        ev = Point2DUtil.plus(ev, e.getRotatedOffset(g2d));
        Double center = Point2DUtil.scale(Point2DUtil.plus(sv, ev), 0.5);
        sv = clip(sv, clipScale, center, !isCreaseVertex(s));
        ev = clip(ev, clipScale, center, !isCreaseVertex(e));
        g2d.draw(new Line2D.Double(
                sv.getX(),
                sv.getY(),
                ev.getX(),
                ev.getY()));
    }

    private static Double clip(
            Double vertex,
            double clipScale,
            Double center,
            boolean clip) {
        if (clip) {
            return Point2DUtil.plus(
                    Point2DUtil.scale(vertex, 1.0 - clipScale),
                    Point2DUtil.scale(center, clipScale));
        } else {
            return vertex;
        }
    }

    private static boolean isCreaseVertex(OriVertex v) {
        for (OriHalfEdge he : v.getHalfEdges()) {
            if (he.getType() != LineType.CREASE) {
                return false;
            }
        }
        return true;
    }

    public static void drawFoldedHalfEdge(
            Graphics2D g2d,
            OriHalfEdge he,
            Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        OriVertex s = he.getSv();
        OriVertex e = he.getEv();

        Double sv = s.getFoldedPosition();
        Double ev = e.getFoldedPosition();
        sv = Point2DUtil.plus(sv, s.getRotatedOffset(g2d));
        ev = Point2DUtil.plus(ev, e.getRotatedOffset(g2d));
        g2d.draw(new Line2D.Double(
                sv.getX(),
                sv.getY(),
                ev.getX(),
                ev.getY()));
    }

}
