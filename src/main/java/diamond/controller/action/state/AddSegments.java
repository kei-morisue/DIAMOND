/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2021 Kei Morisue
 */
package diamond.controller.action.state;

import java.util.Stack;

import diamond.controller.Context;
import diamond.model.cyborg.diagram.step.Step;
import diamond.model.cyborg.geom.PickerCyborg;
import diamond.model.cyborg.geom.d1.AbstractSegment;
import diamond.model.cyborg.geom.d1.SegmentCrease;
import diamond.model.cyborg.geom.d2.Face;
import diamond.model.cyborg.geom.m.MirrorSimple;

/**
 * @author Kei Morisue
 *
 */
public class AddSegments extends AbstractPaintState {
    private Context context;
    private AbstractSegment s0;
    private Stack<AbstractSegment> segments;

    public AddSegments(Context context) {
        this.context = context;
    }

    @Override
    protected void undo() {
    }

    @Override
    protected void executeAction() {
        Step step = context.getDiagram().getStep();
        Face face = step.getFaces().get(0);
        s0 = segments.pop();
        MirrorSimple mirror = new MirrorSimple(s0.getV0(), s0.getV1());
        for (AbstractSegment s : segments) {
            face.add(new SegmentCrease(
                    mirror.apply(s.getV0()),
                    mirror.apply(s.getV1()), //TODO
                    s.getType()));
        }
        step.update();
        context.initialize();
    }

    @Override
    protected boolean tryAction() {
        PickerCyborg<AbstractSegment> picker = context
                .getPicker(AbstractSegment.class);
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
