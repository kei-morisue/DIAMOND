/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.action.state.vertexadd.State0;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.PerpendicularUtil;
import diamond.model.cyborg.util.Point2DUtil;
import diamond.view.ui.screen.draw.PaintScreenDrawer;;

/**
 * @author Kei Morisue
 *
 */
public class VertexAddAction extends AbstractPaintAction {

    @Override
    public void onDraw(Graphics2D g2d, Context context) {
        HalfEdge he = context.getPointer().getHalfEdge();
        Double p = context.getMousePoint();
        if (he == null) {
            return;
        }
        Vertex v0 = he.getV0();
        Vertex v1 = he.getV1();
        double h = Point2DUtil.distanceToSegment(p, v0, v1);
        if (h < Config.EPSILON_SCREEN) {
            Double foot = PerpendicularUtil.foot(p, v0, v1);
            Vertex vertex = new Vertex(foot);
            vertex.getProperty().isPointed = true;
            PaintScreenDrawer.draw(g2d, vertex);
        }
    }

    @Override
    protected void setInitialState() {
        setActionState(new State0());
    }

}
