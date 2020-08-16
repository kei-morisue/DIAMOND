/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import java.util.Stack;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.d1.SegmentBase;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.m.Mirror;

/**
 * @author Kei Morisue
 *
 */
public class AddSegments extends AbstractPaintState {
    private Context context;
    private SegmentBase s0;
    private Stack<SegmentBase> segments;

    public AddSegments(Context context) {
        this.context = context;
    }

    @Override
    protected void undo() {
    }

    @Override
    protected void executeAction() {
        Step step = context.getDiagram().getStep();
        Face face = step.getFaces().get(0);//TODO
        s0 = segments.pop();
        Mirror mirror = new Mirror(s0.getV0(), s0.getV1());
        for (SegmentBase s : segments) {
            face.add(new SegmentCrease(s).mirror(mirror));
        }
        step.update();
        context.initialize();
    }

    @Override
    protected boolean tryAction() {
        PickerCyborg<SegmentBase> picker = context
                .getPicker(SegmentBase.class);
        segments = picker.get();
        return true;
    }

    @Override
    void initialize() {
    }

    @Override
    public AbstractState onMove() {
        return this;
    }

    @Override
    public AbstractState onRelease() {
        return doAction();
    }

}
