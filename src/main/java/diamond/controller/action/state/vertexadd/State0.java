/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexadd;

import java.awt.geom.Point2D.Double;
import java.util.Stack;

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.HalfEdgeSplitter;
import diamond.model.cyborg.util.PerpendicularUtil;
import diamond.model.cyborg.util.Point2DUtil;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends HalfEdgePickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = State0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State0.class;
    }

    @Override
    protected void aftermath(Context context) {
        Stack<HalfEdge> halfEdges = context.getPicker().getHalfEdges();
        if (halfEdges.size() != 1) {
            context.initialize();
            return;
        }
        HalfEdge halfEdge = halfEdges.get(0);
        Vertex v0 = halfEdge.getV0();
        Vertex v1 = halfEdge.getV1();
        Double p = context.getMousePoint();
        double h = Point2DUtil.distanceToSegment(p, v0, v1);
        if (h < Config.EPSILON_SCREEN) {
            Double foot = PerpendicularUtil.foot(p, v0, v1);
            double t = foot.distance(v0) / v1.distance(v0);
            HalfEdgeSplitter.split(halfEdge, t);
            context.fold();
        }
        context.initialize();
    }

}
