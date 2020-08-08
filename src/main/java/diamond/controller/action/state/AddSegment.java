/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import diamond.controller.Context;

/**
 * @author Kei Morisue
 *
 */
public class AddSegment extends AbstractPaintState {
    private Context context;

    public AddSegment(Context context) {
        this.context = context;
    }

    @Override
    protected void undo() {
    }

    @Override
    protected void executeAction() {
        context.getDiagram().getStep().update();
        context.initialize();
    }

    @Override
    protected boolean tryAction() {
        //TODO
        return false;
    }

    @Override
    void initialize() {
    }

    @Override
    public void onMove() {
    }

}
