/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.selectv;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.context.PaintContext;
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
    protected void undoAction(PaintContext context) {
        Set<OriVertex> vertices = context.palette.getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setPickked(false);
        }
    }

    @Override
    protected void onResult(PaintContext context) {
        Set<OriVertex> vertices = context.palette.getOriModel()
                .getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setPickked(true);
        }
        context.getPickedPoints().clear();
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return true;
    }

    @Override
    protected void rebuild(PaintContext context) {
    }

}
