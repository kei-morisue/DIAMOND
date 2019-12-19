/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.CyborgPicker;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.VertexFinder;

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
        CyborgPicker picker = context.getPicker();
        Stack<Vertex> vertices = picker.getVertices();
        if (!vertices.isEmpty()) {
            if (vertices.lastElement() == picked) {
                return false;
            }
        }
        picker.push(picked);
        return true;
    }

    @Override
    public void setPointer(Context context) {
        Vertex picked = VertexFinder.find(context);
        context.getPointer().setVertex(picked);
    }

}
