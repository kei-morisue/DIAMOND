/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PointedElement;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.util.NearestLineFinder;
import diamond.model.geom.util.NearestPointFinder;
import diamond.model.palette.cp.editor.LineDivider;

/**
 * @author long_
 *
 */
public class AddVertexState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setPrevClass(AddVertexState.class);
        setNextClass(AddVertexState.class);

    }

    @Override
    protected void undoAction(Context context) {

    }

    @Override
    protected void onResult(Context context) {
    }

    @Override
    protected void rebuild(Context context) {
        context.getPalette().getCP().rebuildModel();

    }

    @Override
    protected boolean onAction(Context context,
            Double currentPoint) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriPoint pointedOriPoint = pointedElements.getOriPoint();
        OriLine pointedOriLine = pointedElements.getOriLine();
        if (pointedOriPoint != null && pointedOriLine != null) {
            LineDivider.divide(
                    pointedOriLine,
                    pointedOriPoint,
                    context.getPalette().getCP().getLines());
            return true;
        }
        return false;
    }

    @Override
    public void setCandate(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriPoint findAround = NearestPointFinder.findAround(context);
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        pointedElements.setOriPoint(findAround);
        pointedElements.setOriLine(NearestLineFinder.findAround(context));
    }

}
