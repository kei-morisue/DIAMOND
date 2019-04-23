/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offset;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class AnyPointPickkingState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(AnyPointPickkingState.class);
        setNextClass(AnyPointPickkingState.class);
    }

    @Override
    protected void undoAction(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getOriModel().fold();
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        Double p = context.currentLogicalMousePoint;
        if (p != null) {
            Set<OriVertex> vertices = context.palette.getOriModel()
                    .getVertices();
            for (OriVertex v : vertices) {
                if (v.isPickked()) {
                    v.setOffset(p);
                }
            }
            return true;
        }
        return false;
    }

}
