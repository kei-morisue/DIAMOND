/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class UnfoldLineState extends OriLinePickkingState {
    @Override
    protected void initialize() {
        setNextClass(UnfoldLineState.class);
        setPrevClass(UnfoldLineState.class);
    }

    @Override
    protected void onResult(Context context) {

        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine oriLine = paintScreenContext.getPickedLines()
                .get(0);
        if (oriLine.getType() != LineType.CREASE) {
            oriLine.setType(LineType.CREASE);
        } else {
            oriLine.setType(LineType.UNSETTLED_MOUNTAIN);
        }
        paintScreenContext.getPickedLines().clear();
    }

}
