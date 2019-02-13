/**
 * DIAMOND - Origami Editor
 * Copyright (C) 2018 Kei Morisue
 */
package diamond.view.paint.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.orimodel.OriFace;
import diamond.model.geom.element.orimodel.OriHalfEdge;
import diamond.model.geom.element.orimodel.OriModel;
import diamond.model.geom.element.orimodel.OriVertex;
import diamond.view.resource.color.ColorStyle;
import diamond.view.resource.graphic.LineStrokeSetting;

/**
 * @author long_
 *
 */
public class OriDrawer {
    public static void describe(Graphics2D g2d, Object pointed, int x, int y) {
        g2d.setColor(ColorStyle.DEBUGGING_STRING);
        AffineTransform tmpTransform = g2d.getTransform();
        g2d.setTransform(new AffineTransform());
        if (pointed == null) {
            g2d.drawString("-", x, y);
            ;
        } else {
            g2d.drawString(pointed.toString(), x, y);
        }
        g2d.setTransform(tmpTransform);
        return;
    }

    public static void drawLine(Graphics2D g2d, OriLine line, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(line.p0.x, line.p0.y, line.p1.x,
                line.p1.y));
    }

    public static void drawHalfEdge(Graphics2D g2d, OriHalfEdge he, Color color,
            Stroke stroke) {
        g2d.setColor(color);
        g2d.setStroke(stroke);
        g2d.draw(new Line2D.Double(he.getSv().x, he.getSv().y, he.getEv().x,
                he.getEv().y));
    }

    public static void drawPoint(Graphics2D g2d, Vector2d point,
            double size, Color color) {
        double scaledSize = size;
        g2d.setColor(color);
        g2d.fill(new Rectangle2D.Double(
                point.x - scaledSize * 0.5,
                point.y - scaledSize * 0.5,
                scaledSize,
                scaledSize));
    }

    public static void drawVertex(Graphics2D g2d, OriVertex vertex, double size,
            Color color) {
        double scaledSize = size;
        g2d.setColor(color);
        Vector2d p = vertex;
        g2d.fill(new Rectangle2D.Double(
                p.x - scaledSize * 0.5,
                p.y - scaledSize * 0.5,
                scaledSize,
                scaledSize));
    }

    public static void drawFace(Graphics2D g2d,
            OriFace face, Color color) {
        g2d.setColor(color);
        g2d.fill(face.getOutline());
    }

    public static void drawModel(Graphics2D g2d, OriModel model) {
        GeneralPath path;
        Point2D p0 = new Point2D.Double();
        Point2D p1 = new Point2D.Double();
        for (OriFace face : model.getFaces()) {
            path = null;
            for (OriHalfEdge he : face.getHalfEdges()) {
                Vector2d v = he.getSv();
                Point2D ptSrc = new Point2D.Double(v.x, v.y);
                face.getTransform().transform(ptSrc, p0);
                if (path == null) {
                    path = new GeneralPath();
                    path.moveTo(p0.getX(), p0.getY());
                } else {
                    path.lineTo(p0.getX(), p0.getY());
                }
            }
            g2d.setColor((face.isFaceFront()) ? ColorStyle.ORIFACE_FRONT
                    : ColorStyle.ORIFACE_BACK);
            path.closePath();
            g2d.fill(path);
            for (OriHalfEdge aux : face.getAuxLines()) {
                Point2D ptSrc0 = new Point2D.Double(aux.getSv().x,
                        aux.getSv().y);
                Point2D ptSrc1 = new Point2D.Double(aux.getEv().x,
                        aux.getEv().y);

                face.getTransform().transform(ptSrc0, p0);
                face.getTransform().transform(ptSrc1, p1);
                g2d.setColor(ColorStyle.ORIHALFEDGE_AUX);
                g2d.setStroke(LineStrokeSetting.STROKE_CREASE);
                g2d.draw(new Line2D.Double(p0.getX(), p0.getY(), p1.getX(),
                        p1.getY()));
            }

            for (OriHalfEdge he : face.getHalfEdges()) {
                Point2D ptSrc0 = new Point2D.Double(he.getSv().x,
                        he.getSv().y);
                Point2D ptSrc1 = new Point2D.Double(he.getEv().x,
                        he.getEv().y);

                face.getTransform().transform(ptSrc0, p0);
                face.getTransform().transform(ptSrc1, p1);
                g2d.setColor(ColorStyle.ORIHALFEDGE);
                g2d.setStroke(LineStrokeSetting.STROKE_EDGE);
                g2d.draw(new Line2D.Double(p0.getX(), p0.getY(), p1.getX(),
                        p1.getY()));
            }
        }
    }
}
