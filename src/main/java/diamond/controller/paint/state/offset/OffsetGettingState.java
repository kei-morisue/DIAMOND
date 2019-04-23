/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offset;

import java.awt.geom.Point2D.Double;
import java.util.Set;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.DistanceUtil;
import diamond.model.geom.util.Point2DUtil;

/**
 * @author long_
 *
 */
public class OffsetGettingState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(OffsetGettingState.class);
        setNextClass(OffsetGettingState.class);
    }

    @Override
    protected void undoAction(PaintContext context) {
        Set<OriVertex> vertices = context.palette.getOriModel()
                .getVertices();
        for (OriVertex v : vertices) {
            if (v.isPickked()) {
                v.setOffset(.0, .0);
                Set<OriLine> lines = context.palette.getCP().getLines();
                for (OriLine l : lines) {
                    if (DistanceUtil.Distance(v, l.p0) < Constants.EPS) {
                        l.p0.setOffset(.0, .0);
                    } else {
                        if (DistanceUtil.Distance(v,
                                l.p1) < Constants.EPS) {
                            l.p1.setOffset(.0, .0);
                        }
                    }
                }
            }
        }
        context.palette.getOriModel().fold();
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
                    Double offset = Point2DUtil.scale(p, .1);
                    v.setOffset(offset);
                    Set<OriLine> lines = context.palette.getCP().getLines();
                    for (OriLine l : lines) {
                        if (DistanceUtil.Distance(v, l.p0) < Constants.EPS) {
                            l.p0.setOffset(offset);
                        } else {
                            if (DistanceUtil.Distance(v,
                                    l.p1) < Constants.EPS) {
                                l.p1.setOffset(offset);
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

}
