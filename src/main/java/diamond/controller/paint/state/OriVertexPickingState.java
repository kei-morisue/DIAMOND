/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.controller.paint.context.PointedElement;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.DistanceUtil;

/**
 * @author long_
 *
 */
public abstract class OriVertexPickingState extends OriPointPickkingState {

    @Override
    public void setCandate(Context context) {
        super.setCandate(context);
        Palette palette = context.getPalette();
        OriModel oriModel = palette.getOriModel();
        Set<OriVertex> vertices = oriModel.getVertices();
        for (OriVertex vertex : vertices) {
            vertex.setPickked(false);
        }
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext
                .getPointedElements();
        OriPoint p = pointedElements.getOriPoint();
        for (OriVertex vertex : vertices) {
            if (p != null) {
                if (DistanceUtil.distance(p, vertex) < Constants.EPS) {
                    vertex.setPickked(true);
                }
            }
        }
    }

}
