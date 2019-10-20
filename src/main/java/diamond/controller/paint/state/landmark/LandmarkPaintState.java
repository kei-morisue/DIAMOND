/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.landmark;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PointedElement;
import diamond.controller.paint.state.OriPointPickkingState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriVertex;

/**
 * @author long_
 *
 */
public class LandmarkPaintState extends OriPointPickkingState {

    public LandmarkPaintState() {
    }

    @Override
    protected void undoAction(Context context) {
        for (OriLine l : context.getPalette().getCP().getLines()) {
            OriPoint p0 = l.p0;
            p0.disableLandmark();
            p0.getV().disableLandmark();
            OriPoint p1 = l.p1;
            p1.disableLandmark();
            p1.getV().disableLandmark();
        }
        rebuild(context);
    }

    @Override
    protected void onResult(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
    }

    @Override
    protected boolean onAction(Context context, Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriPoint p = pointedElements.getOriPoint();
        if (p != null) {
            for (OriLine l : context.getPalette().getCP().getLines()) {
                OriPoint p0 = l.p0;
                if (p.distance(p0) < Constants.EPS) {
                    p0.flipLandmark();
                    OriVertex v = p0.getV();
                    v.flipLandmark();
                }
                OriPoint p1 = l.p1;
                if (p.distance(p1) < Constants.EPS) {
                    OriVertex v = p1.getV();
                    p1.flipLandmark();
                    v.flipLandmark();
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
