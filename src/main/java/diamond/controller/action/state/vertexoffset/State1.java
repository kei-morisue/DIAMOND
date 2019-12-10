/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2020 Kei Morisue
 */
package diamond.controller.action.state.vertexoffset;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import diamond.controller.Context;
import diamond.controller.action.state.AbstractState;

/**
 * @author Kei Morisue
 *
 */
public class State1 extends AbstractState {
    private Point2D.Double Offset = new Point2D.Double();

    @Override
    protected void setNextClass() {
        nextStateClass = State0.class;
    }

    @Override
    protected void setPrevClass() {
        prevStateClass = State0.class;
    }

    @Override
    protected void undo(Context context) {
        context.getPicker().popVertex();
        context.setPaintScreen("paint");
    }

    @Override
    protected void aftermath(Context context) {
        //TODO
        context.setPaintScreen("paint");
    }

    @Override
    protected boolean act(Context context) {
        return true;
    }

    @Override
    public void setPointer(Context context) {
        Double p = context.getMousePoint();
    }

}
