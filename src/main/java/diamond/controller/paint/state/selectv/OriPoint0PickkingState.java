/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.selectv;

import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.DistanceUtil;

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
    protected void undoAction(Context context) {
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setPickked(false);
        }
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        OriPoint p = pickedElements.getOriPoints().get(0);
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            if (DistanceUtil.distance(p, vertex) < Constants.EPS) {
                vertex.setPickked(!vertex.isPickked());
            }
        }
        paintScreenContext.initialize();
    }

    @Override
    protected void rebuild(Context context) {
    }

}
