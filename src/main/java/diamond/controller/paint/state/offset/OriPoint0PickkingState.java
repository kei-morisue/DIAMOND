/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offset;

import java.util.Set;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class OriPoint0PickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OriPoint0PickkingState.class);

    }

    @Override
    protected void onResult(PaintContext context) {
        OriPoint p = context.getPickedPoints().get(0);
        Set<OriVertex> vertices = context.palette.getOriModel().getVertices();

        context.getPickedPoints().clear();

    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getOriModel().fold();

    }

}
