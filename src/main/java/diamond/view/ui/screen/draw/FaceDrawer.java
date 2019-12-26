/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.view.ui.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
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
        for (HalfEdge he : face.getSortedEdges()) {
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
        ArrayList<HalfEdge> halfEdges = face.getSortedEdges();
        for (HalfEdge he : halfEdges) {
            Vertex v0 = he.getV0();
            Point2D p = v0.getFoldedOffset();
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

    private static final int size0 = 10;
    private static final int size1 = size0 >> 1;

    public static TexturePaint getIchimatsu() {
        BufferedImage bi = new BufferedImage(size0, size0,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D bg = bi.createGraphics();
        Rectangle r = new Rectangle(0, 0, size0, size0);
        bg.setColor(Color.white);
        bg.fillRect(0, 0, size1, size1);
        bg.fillRect(size1, size1, size1, size1);
        bg.setColor(Color.gray);
        bg.fillRect(size1, 0, size1, size1);
        bg.fillRect(0, size1, size1, size1);
        return new TexturePaint(bi, r);
    }
}
