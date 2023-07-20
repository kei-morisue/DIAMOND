/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexoffsetauto;

import java.awt.geom.Point2D;
import java.util.HashSet;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;
import diamond.model.cyborg.Vertex;
import diamond.model.cyborg.util.OffsetUtil;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends AbstractState {

	@Override
	protected void setNextClass() {
		nextStateClass = State1.class;
	}

	@Override
	protected void setPrevClass() {
		prevStateClass = State1.class;
	}

	@Override
	protected void undo(Context context) {
		HashSet<Vertex> vertices = context.getCp().getVertices();
		vertices.forEach(v -> v.setOffset(new Point2D.Double()));
		aftermath(context);
	}

	@Override
	protected void aftermath(Context context) {
		context.setPaintScreen("paint");
		context.initialize();
	}

	@Override
	protected boolean act(Context context) {
		return true;
	}

	@Override
	public void setPointer(Context context) {
		OffsetUtil.offset(context);
	}

}
