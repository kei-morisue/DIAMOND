/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.model.geom.element.diagram;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import diamond.view.screen.draw.style.LineStyle;
import diamond.view.screen.draw.style.color.OriArrow;

/**
 * @author long_
 *
 */
public class Arrow {
    private ArrowHead arrowHead;
    private CurvedArrowBody curvedArrowBody;

    public Arrow(Point2D.Double sp, Point2D.Double ep, double size) {
        curvedArrowBody = new CurvedArrowBody(sp, ep, true);
        arrowHead = new ArrowHead(curvedArrowBody, size, false);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(OriArrow.ARROW_BODY);
        g2d.setStroke(LineStyle.STROKE_ARROW);
        g2d.draw(curvedArrowBody.getStroke());
        g2d.setColor(OriArrow.ARROW_VALLEY);
        g2d.fill(arrowHead.getShape());
    }
}
