/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state;

import diamond.controller.Context;
import diamond.model.cyborg.Vertex;
import diamond.model.math.VertexFinder;

/**
 * @author Kei Morisue
 *
 */
public abstract class VertexPickingState extends AbstractState {

    @Override
    protected void undo(Context context) {
        context.getPicker().popVertex();
    }

    @Override
    protected boolean act(Context context) {
        Vertex picked = context.getPointer().getVertex();
        if (picked == null) {
            return false;
        }
        context.getPicker().push(picked);
        return true;
    }

    @Override
    public void setCandidate(Context context) {
        Vertex picked = VertexFinder.find(context);
        context.getPointer().setVertex(picked);
    }

}
