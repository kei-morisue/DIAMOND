/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.awt.geom.Point2D.Double;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.OriFaceUtil;

/**
 * @author long_
 *
 */
public class OriFacePickkingState extends AbstractPaintState {

    @Override
    protected void initialize() {
        setNextClass(OriFacePickkingState.class);
        setPrevClass(OriFacePickkingState.class);

    }

    @Override
    protected void undoAction(PaintContext context) {
    }

    @Override
    protected void onResult(PaintContext context) {
        Double point = context.currentLogicalMousePoint;
        OriVertex orivertex = new OriVertex(point.x, point.y);
        for (OriFace face : context.getCp().getOriModel().getFaces()) {
            if (OriFaceUtil.onFace(face, orivertex)) {
                context.pointedOriFace = face;
                context.getPickedOriFaces().push(face);
            }
        }
    }

    @Override
    protected boolean onAction(PaintContext context, Double currentPoint) {
        return true;
    }

}
