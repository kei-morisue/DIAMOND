/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom1;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.FaceSplitter;

/**
 * @author Kei Morisue
 *
 */
public class state1 extends VertexPickingState {

    @Override
    protected void setNextClass() {
        nextStateClass = state0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = state1.class;
    }

    @Override
    protected void aftermath(Context context) {
        Stack<Vertex> vertices = context.getPicker().getVertices();
        Vertex v0 = vertices.get(0);
        Vertex v1 = vertices.get(1);
        Cp cp = context.getCp();
        Face face = cp.getFaces().get(0);//TODO select proper face
        EdgeType type = context.getInputType();
        HalfEdge h0 = new HalfEdge(v0, v1, type);
        if (!EdgeType.isSettled(type)) {
            h0.getProperty().setDisabled(true);
        }
        Face[] faces = FaceSplitter.split(face, h0);
        cp.add(faces[0]);
        cp.add(faces[1]);
        cp.remove(face);
        cp.add(h0);
        context.initialize();
    }

}
