/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.selectv;

import java.util.Set;

import diamond.controller.paint.context.PaintContext;
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
    protected void undoAction(PaintContext context) {
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setPickked(false);
        }
    }

    @Override
    protected void onResult(PaintContext context) {
        OriPoint p = context.getPickedPoints().get(0);
        Set<OriVertex> vertices = context.getPalette().getOriModel().getVertices();
        for (OriVertex vertex : vertices) {
            if (DistanceUtil.distance(p, vertex) < Constants.EPS) {
                vertex.setPickked(!vertex.isPickked());
            }
        }
        context.getPickedPoints().clear();
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

}
