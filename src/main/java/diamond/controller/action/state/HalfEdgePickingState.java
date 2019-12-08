/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.CyborgPicker;
import diamond.model.cyborg.HalfEdge;
import diamond.model.cyborg.util.HalfEdgeFinder;

/**
 * @author Kei Morisue
 *
 */
public abstract class HalfEdgePickingState extends AbstractState {

    @Override
    protected void undo(Context context) {
        context.getPicker().popHalfEdge();
    }

    @Override
    protected boolean act(Context context) {
        HalfEdge picked = context.getPointer().getHalfEdge();
        if (picked == null) {
            return false;
        }
        CyborgPicker picker = context.getPicker();
        Stack<HalfEdge> halfEdges = picker.getHalfEdges();
        if (!halfEdges.isEmpty()) {
            if (halfEdges.lastElement() == picked) {
                return false;
            }
        }
        picker.push(picked);
        return true;
    }

    @Override
    public void setPointer(Context context) {
        HalfEdge picked = HalfEdgeFinder.find(context);
        context.getPointer().setHalfEdge(picked);
    }

}
