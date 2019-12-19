/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.model.cyborg.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.HashMap;

import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.symbol.Symbol;
import diamond.view.ui.screen.draw.G2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class CpScaler {

    public static double getScale(Graphics2D g2d, Cp cp) {
        Double clip = getClip(cp);
        Dimension dimension = G2DUtil.getDimension(g2d);
        double w = dimension.getWidth();
        double h = dimension.getHeight();
        return Math.min(w / clip.getWidth(), h / clip.getHeight());
    }

    private static Rectangle2D.Double getClip(Cp cp) {
        Rectangle2D.Double r = null;
        HashMap<HalfEdge, Symbol<HalfEdge>> symbols = cp.getSymbolsHalfEdge();
        for (HalfEdge he : cp.getHalfEdges()) {
            if (!EdgeType.isSettled(he.getType())) {
                if (r == null) {
                    r = he.clip();
                } else {
                    r.add(he.clip());
                }
            }
            if (symbols.containsKey(he)) {
                if (r == null) {
                    r = symbols.get(he).clip();
                } else {
                    r.add(symbols.get(he).clip());
                }
            }
        }
        if (r == null) {
            for (Face face : cp.getFaces()) {
                if (r == null) {
                    r = face.clip();
                } else {
                    r.add(face.clip());
                }
            }
        }
        double theta = cp.getTransform().getTheta();
        double w = r.getWidth();
        double h = r.getHeight();
        Point2D.Double p0 = Point2DUtil
                .rotate(new Point2D.Double(w * 0.5, h * 0.5), theta);
        Point2D.Double p1 = Point2DUtil
                .rotate(new Point2D.Double(-w * 0.5, h * 0.5), theta);
        Point2D.Double p2 = Point2DUtil
                .rotate(new Point2D.Double(w * 0.5, -h * 0.5), theta);
        Point2D.Double p3 = Point2DUtil
                .rotate(new Point2D.Double(-w * 0.5, -h * 0.5), theta);
        double x0 = Math.min(Math.min(Math.min(p0.x, p1.x), p2.x), p3.x);
        double y0 = Math.min(Math.min(Math.min(p0.y, p1.y), p2.y), p3.y);
        double x1 = Math.max(Math.max(Math.max(p0.x, p1.x), p2.x), p3.x);
        double y1 = Math.max(Math.max(Math.max(p0.y, p1.y), p2.y), p3.y);
        return new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
    }
}
