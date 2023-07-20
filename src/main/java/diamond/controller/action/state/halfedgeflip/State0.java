/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.halfedgeflip;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.HalfEdgePickingState;
import diamond.model.cyborg.EdgeType;
import diamond.model.cyborg.HalfEdge;

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
		nextStateClass = State0.class;
	}

	@Override
	protected void aftermath(Context context) {
		Stack<HalfEdge> halfEdges = context.getPicker().getHalfEdges();
		if (halfEdges.size() != 1) {
			context.initialize();
			return;
		}
		HalfEdge he = halfEdges.get(0);
		if (he.getType() != EdgeType.CUT) {
			he.flip();
		}
		if (he.getType() == EdgeType.MOUNTAIN || he.getType() == EdgeType.VALLEY) {
			context.fold();
		}
		context.initialize();
	}

}
