/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.context;

import java.awt.Graphics2D;
import java.util.Stack;

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
public class PickedElements implements Drawable {

    private Stack<OriPoint> oriPoints = new Stack<>();
    private Stack<OriLine> oriLines = new Stack<>();
    private Stack<OriFace> oriFaces = new Stack<>();

    public PickedElements() {

    }

    public void clear() {
        oriLines.clear();
        oriPoints.clear();
        oriFaces.clear();
    }

    public void popOriPoint() {
        if (oriPoints.isEmpty()) {
            return;
        }
        oriPoints.pop();
    }

    public void pushOriPoint(OriPoint p) {
        oriPoints.push(p);
    }

    public Stack<OriPoint> getOriPoints() {
        return oriPoints;
    }

    public void setOriPoints(Stack<OriPoint> oriPoints) {
        this.oriPoints = oriPoints;
    }

    public Stack<OriLine> getOriLines() {
        return oriLines;
    }

    public void setOriLines(Stack<OriLine> oriLines) {
        this.oriLines = oriLines;
    }

    public Stack<OriFace> getOriFaces() {
        return oriFaces;
    }

    public void setOriFaces(Stack<OriFace> oriFaces) {
        this.oriFaces = oriFaces;
    }

    @Override
    public void draw(Graphics2D g2d) {
        drawFaces(g2d);
        drawLines(g2d);
        drawPoints(g2d);
    }

    private void drawLines(Graphics2D g2d) {
        for (int i = 0; i < oriLines.size(); i++) {
            OriLineDrawer.drawLine(
                    g2d,
                    oriLines.get(i),
                    OriLineColor.PICKED,
                    LineStyle.STROKE_PICKED);
        }
    }

    private void drawPoints(Graphics2D g2d) {
        for (int i = 0; i < oriPoints.size(); i++) {
            OriPoint point = oriPoints.get(i);
            double size = VertexStyle.SIZE_PICKED / G2DUtil.getScale(g2d);
            OriPointDrawer.drawPoint(
                    g2d, point,
                    size,
                    OriPointColor.ORIPOINT_PICKED);
        }
    }

    private void drawFaces(Graphics2D g2d) {
        for (OriFace face : oriFaces) {
            OriFaceDrawer.drawFace(g2d, face, OriFaceColor.ORI_FACE_PICKED);
        }
    }
}
