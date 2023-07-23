/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.facebase;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.action.state.FacePickingState;
import diamond.model.cyborg.Cp;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.fold.Folder;

/**
 * @author Kei Morisue
 *
 */
public class State0 extends FacePickingState {

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
		Stack<Face> faces = context.getPicker().getFaces();
		if (faces.size() != 1) {
			context.initialize();
			return;
		}
		Face face = faces.get(0);
		Cp cp = context.getCp();
		cp.setBaseFace(face);
		Folder.fold(cp);
		context.initialize();
	}

}
