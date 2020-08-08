/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import java.util.Stack;

import diamond.controller.Context;
import diamond.controller.mouse.PickerCyborg;
import diamond.model.cyborg.geom.d0.Vertex;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.step.Step;

/**
 * @author Kei Morisue
 *
 */
public class AddSegment extends PickCyborg<Vertex>//TODO
{
    private Context context;
    private Step step;
    private Vertex v0;
    private Vertex v1;

    public AddSegment(Context context) {
        super(context, Vertex.class);
        this.context = context;
        this.step = context.getDiagram().getStep();
    }

    @Override
    protected void undo() {
    }

    @Override
    protected void executeAction() {
        step.getFaces().get(0).getCreases().add(new SegmentCrease(v0,
                v1, context.getType()));
        step.update();
        context.initialize();
    }

    @Override
    protected boolean tryAction() {
        PickerCyborg<Vertex> picker = getPicker();
        Stack<Vertex> picked = picker.get();
        this.v1 = picked.get(1);
        this.v0 = picked.get(0);
        return v1 != v0;
    }

    @Override
    void initialize() {
    }

    @Override
    public void onMove() {
    }

}
