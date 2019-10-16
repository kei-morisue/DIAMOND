/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.baseface;

import diamond.controller.paint.context.PaintContext;
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
    protected void onResult(PaintContext context) {
        OriFace baseFace = context.getPointedOriFace();
        OriModel oriModel = context.palette.getOriModel();
        if (oriModel.getFaces().size() == 1) {
            oriModel.flip();
        }
        if (baseFace != null) {
            oriModel
                    .setBaseFace(baseFace);
            context.palette.getCP()
                    .setBaseFaceCenter(OriFaceUtil.getCenterPoint(baseFace));
        }
        context.palette.getCP().saveOrder();
        context.initialize();
    }

    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());
    }
}
