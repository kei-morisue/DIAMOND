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

import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class OriHalfEdgeDrawer {

    public static void drawFoldedAuxLine(
            Graphics2D g2d,
            OriHalfEdge he,
            Color color,
            Stroke stroke,
            double clipScale) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        Double sv = he.getSv().getFoldedPosition();
        Double ev = he.getEv().getFoldedPosition();
        sv = Point2DUtil.plus(sv, he.getSv().getOffset());
        ev = Point2DUtil.plus(ev, he.getEv().getOffset());
        Double center = Point2DUtil.scale(Point2DUtil.plus(sv, ev), 0.5);
        sv = Point2DUtil.plus(
                Point2DUtil.scale(sv, 1.0 - clipScale),
                Point2DUtil.scale(center, clipScale));
        ev = Point2DUtil.plus(
                Point2DUtil.scale(ev, 1.0 - clipScale),
                Point2DUtil.scale(center, clipScale));
        g2d.draw(new Line2D.Double(
                sv.getX(),
                sv.getY(),
                ev.getX(),
                ev.getY()));
    }

    public static void drawFoldedHalfEdge(
            Graphics2D g2d,
            OriHalfEdge he,
            Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        Double sv = he.getSv().getFoldedPosition();
        Double ev = he.getEv().getFoldedPosition();
        sv = Point2DUtil.plus(sv, he.getSv().getOffset());
        ev = Point2DUtil.plus(ev, he.getEv().getOffset());
        g2d.draw(new Line2D.Double(
                sv.getX(),
                sv.getY(),
                ev.getX(),
                ev.getY()));
    }

}
