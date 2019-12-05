/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state;

import diamond.controller.Context;
import diamond.model.cyborg.Face;
import diamond.model.cyborg.util.FaceFinder;

/**
 * @author Kei Morisue
 *
 */
public abstract class FacePickingState extends AbstractState {

    @Override
    protected void undo(Context context) {
        context.getPicker().popFace();
    }

    @Override
    protected boolean act(Context context) {
        Face picked = context.getPointer().getFace();
        if (picked == null) {
            return false;
        }
        context.getPicker().push(picked);
        return true;
    }

    @Override
    public void setPointer(Context context) {
        Face picked = FaceFinder.find(context);
        context.getPointer().setFace(picked);
    }

}
