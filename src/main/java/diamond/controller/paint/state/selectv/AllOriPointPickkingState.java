/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.selectv;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class AllOriPointPickkingState extends OriPointPickkingState {

    @Override
    protected void initialize() {
        setPrevClass(AllOriPointPickkingState.class);
        setNextClass(AllOriPointPickkingState.class);

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
        Set<OriVertex> vertices = context.getPalette().getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setPickked(true);
        }
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        paintScreenContext.getPickedPoints().clear();
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        return true;
    }

    @Override
    protected void rebuild(Context context) {
    }

}
