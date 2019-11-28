/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.axiom1;

import java.util.ArrayList;
import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.VertexPickingState;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.CrossPointUtil;
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

        EdgeType type = context.getInputType();
        ArrayList<Face> toBeDeleted = new ArrayList<Face>();
        ArrayList<Face> toBeAdded = new ArrayList<Face>();
        for (Face face : cp.getFaces()) {
            ArrayList<Vertex> crossPoints = CrossPointUtil.getCrossPoints(face,
                    v0, v1);
            if (crossPoints.size() == 2) {
                HalfEdge he = new HalfEdge(crossPoints.get(0),
                        crossPoints.get(1), type);
                Face[] faces = FaceSplitter.split(face, he);
                toBeAdded.add(faces[0]);
                toBeAdded.add(faces[1]);
                toBeDeleted.add(face);
            }
        }
        for (Face face : toBeDeleted) {
            cp.remove(face);
        }
        for (Face face : toBeAdded) {
            cp.add(face);
        }

        context.initialize();
    }

}
