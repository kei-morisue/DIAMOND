/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.offset;

import java.util.Set;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.Palette;
import diamond.controller.paint.context.PickedElements;
import diamond.controller.paint.state.OriVertexPickingState;
import diamond.model.geom.Constants;
import diamond.model.geom.element.cp.OriPoint;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.element.origami.OriVertex;
import diamond.model.geom.util.DistanceUtil;

/**
 * @author long_
 *
 */
public class OriPoint0PickkingState extends OriVertexPickingState {
    @Override
    protected void initialize() {
        setPrevClass(OriPoint0PickkingState.class);
        setNextClass(OffsetSettingState.class);
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PickedElements pickedElements = paintScreenContext.getPickedElements();
        OriPoint p = pickedElements.getOriPoints().get(0);
        Palette palette = context.getPalette();
        OriModel oriModel = palette.getOriModel();
        Set<OriVertex> vertices = oriModel.getVertices();
        for (OriVertex vertex : vertices) {
            if (DistanceUtil.distance(p, vertex) < Constants.EPS) {
                vertex.setPickked(true);
            }
        }
    }

    @Override
    protected void rebuild(Context context) {
    }

}
