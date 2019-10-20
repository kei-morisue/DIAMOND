/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.awt.Graphics2D;

import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriFace;
import diamond.view.screen.draw.G2DUtil;
import diamond.view.screen.draw.OriFaceDrawer;
import diamond.view.screen.draw.OriLineDrawer;
import diamond.view.screen.draw.OriPointDrawer;
import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.VertexStyle;
import diamond.view.screen.draw.style.color.OriFaceColor;
import diamond.view.screen.draw.style.color.OriLineColor;
import diamond.view.screen.draw.style.color.OriPointColor;

/**
 * @author long_
 *
 */
public class PointedElement implements Drawable {

    private OriPoint oriPoint = null;
    private OriLine oriLine = null;
    private OriFace oriFace = null;

    public PointedElement() {

    }

    public void clear() {
        oriLine = null;
        oriPoint = null;
        oriFace = null;
    }

    public OriPoint getOriPoint() {
        return oriPoint;
    }

    public void setOriPoint(OriPoint oriPoint) {
        this.oriPoint = oriPoint;
    }

    public OriLine getOriLine() {
        return oriLine;
    }

    public void setOriLine(OriLine oriLine) {
        this.oriLine = oriLine;
    }

    public OriFace getOriFace() {
        return oriFace;
    }

    public void setOriFace(OriFace oriFace) {
        this.oriFace = oriFace;
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawPointedFace(g2d);
        drawPointedLine(g2d);
        drawPointedPoint(g2d);
    }

    private void drawPointedPoint(Graphics2D g2d) {
        if (oriPoint != null) {
            double size = VertexStyle.SIZE_POINTED
                    / G2DUtil.getScale(g2d);
            OriPointDrawer.drawPoint(
                    g2d,
                    oriPoint,
                    size,
                    OriPointColor.ORIPOINT_POINTED);
        }
    }

    private void drawPointedLine(Graphics2D g2d) {
        if (oriLine != null) {
            OriLineDrawer.drawLine(
                    g2d,
                    oriLine,
                    OriLineColor.POINTED,
                    LineStyle.STROKE_POINTED);
        }
    }

    private void drawPointedFace(Graphics2D g2d) {
        if (oriFace != null) {
            OriFaceDrawer.drawFace(g2d, oriFace, OriFaceColor.ORI_FACE_POINTED);
        }
    }
}
