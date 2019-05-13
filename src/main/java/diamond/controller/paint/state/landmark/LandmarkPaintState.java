/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.landmark;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.AbstractPaintState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.DistanceUtil;

/**
 * @author long_
 *
 */
public class LandmarkPaintState extends AbstractPaintState {

    public LandmarkPaintState() {
    }

    @Override
    protected void undoAction(PaintContext context) {
        for (OriLine l : context.palette.getCP().getLines()) {
            l.p0.setLandmark(false);
            l.p1.setLandmark(false);
        }
        rebuild(context);
    }

    @Override
    protected void onResult(PaintContext context) {
    }

    @Override
    protected void rebuild(PaintContext context) {
        context.palette.getCP().rebuildModel();
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        OriPoint p = context.pointedOriPoint;
        if (p != null) {
            for (OriLine l : context.palette.getCP().getLines()) {
                OriPoint p0 = l.p0;
                if (DistanceUtil.distance(p, p0) < Constants.EPS) {
                    p0.setLandmark(!p0.isLandmark());
                }
                OriPoint p1 = l.p1;
                if (DistanceUtil.distance(p, p1) < Constants.EPS) {
                    p1.setLandmark(!p1.isLandmark());
                }
            }
            return true;
        }

        return false;
    }

    @Override
    protected void initialize() {
        setPrevClass(LandmarkPaintState.class);
        setNextClass(LandmarkPaintState.class);
    }

}
