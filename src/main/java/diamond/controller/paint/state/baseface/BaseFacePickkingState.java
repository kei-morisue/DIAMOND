/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.baseface;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.controller.paint.context.PointedElement;
import diamond.controller.paint.state.OriFacePickkingState;
import diamond.model.geom.element.origami.OriFace;
import diamond.model.geom.element.origami.OriModel;
import diamond.model.geom.util.OriFaceUtil;

/**
 * @author long_
 *
 */
public class BaseFacePickkingState extends OriFacePickkingState {
    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        PointedElement pointedElements = paintScreenContext.getPointedElements();
        OriFace baseFace = pointedElements.getOriFace();
        OriModel oriModel = context.getPalette().getOriModel();
        if (oriModel.getFaces().size() == 1) {
            oriModel.flip();
        }
        if (baseFace != null) {
            oriModel
                    .setBaseFace(baseFace);
            context.getPalette().getCP()
                    .setBaseFaceCenter(OriFaceUtil.getCenterPoint(baseFace));
        }
        context.getPalette().getCP().saveOrder();
        context.initialize();
    }

    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());
    }
}
