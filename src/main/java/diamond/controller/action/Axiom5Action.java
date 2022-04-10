/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.action.state.axiom5.State0;
import diamond.model.cyborg.Vertex;
import diamond.view.ui.screen.style.HalfEdgeStyle;

/**
 * @author Kei Morisue
 *
 */
public class Axiom5Action extends AbstractPaintAction {

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        //TODO tentative segment, 60 degrees
        Vertex vertex = context.getPointer().getVertex();
        if (vertex != null) {
            g2d.setColor(HalfEdgeStyle.POINTED);
            g2d.setStroke(HalfEdgeStyle.STROKE_TEMPORARY);
            double size = Config.PAPER_SIZE * 10;
            Point2D.Double p1 = new Point2D.Double(
                    vertex.x + size * Math.cos(Math.PI / 3),
                    vertex.y + size * Math.sin(Math.PI / 3));
            g2d.draw(new Line2D.Double(vertex.x, vertex.y,
                    p1.x,
                    p1.y));
        }
    }

    @Override
    protected void setInitialState() {
        setActionState(new State0());
    }

}
