/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.view.screen.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriHalfEdge;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriModelUtil;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class OriFaceDrawer {

    public static void drawFace(Graphics2D g2d,
            OriFace face, Color color) {
        g2d.setColor(color);
        g2d.fill(face.getOutline());
    }

    public static void drawFoldedFace(Graphics2D g2d,
            OriFace face, Color color) {
        g2d.setColor(color);
        g2d.fill(getFoldedOutline(g2d, face));
    }

    public static void drawBaseFace(Graphics2D g2d,
            OriModel oriModel) {
        g2d.setColor(diamond.view.screen.draw.style.color.OriFaceColor
                .getBaseFaceColor(oriModel));
        OriFace baseFace = oriModel.getBaseFace();
        g2d.fill(baseFace.getOutline());
        OriVertex centerPoint = OriModelUtil.getCenterPoint(baseFace);
        drawCross(g2d, (int) centerPoint.x, (int) centerPoint.y);
    }

    private static void drawCross(
            Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.red);
        int size = (int) (2.0 / G2DUtil.getScale(g2d));
        g2d.fillRect(x - 3 * size, y - size, 6 * size, 2 * size);
        g2d.fillRect(x - size, y - 3 * size, 2 * size, 6 * size);
    }

    public static GeneralPath getFoldedOutline(Graphics2D g2d, OriFace face) {
        GeneralPath foldedOutline = null;
        ArrayList<OriHalfEdge> halfEdges = face.getHalfEdges();
        for (OriHalfEdge he : halfEdges) {
            OriVertex sv = he.getSv();
            Point2D p = Point2DUtil.plus(
                    sv.getFoldedPosition(),
                    sv.getOffset());
            double x = p.getX();
            double y = p.getY();
            if (foldedOutline == null) {
                foldedOutline = new GeneralPath();
                foldedOutline.moveTo(x, y);
            } else {
                foldedOutline.lineTo(x, y);
            }
        }
        foldedOutline.closePath();
        return foldedOutline;
    }

}
