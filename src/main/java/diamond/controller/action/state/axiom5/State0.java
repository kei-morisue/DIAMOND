/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom5;

import java.awt.geom.Point2D;
import java.util.Stack;

import diamond.Config;
import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.HalfEdgeAdder;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends VertexPickingState {

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
        //TODO tentative implementation
        Stack<Vertex> vertices = context.getPicker().getVertices();
        if (vertices.size() != 1) {
            context.initialize();
            return;
        }
        Vertex v0 = vertices.get(0);

        Point2D.Double p1 = p1(v0);
        HalfEdgeAdder.add(context, v0,
                p1);
        context.fold();
        context.initialize();
    }

    private Point2D.Double p1(Vertex v0) {
        double size = Config.PAPER_SIZE * 10;
        Point2D.Double p1 = new Point2D.Double(
                v0.x + size * Math.cos(Math.PI / 3),
                v0.y + size * Math.sin(Math.PI / 3));
        return p1;
    }

}
