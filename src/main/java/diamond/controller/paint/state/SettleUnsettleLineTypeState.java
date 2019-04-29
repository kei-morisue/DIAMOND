/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import diamond.controller.paint.PaintContext;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class SettleUnsettleLineTypeState extends OriLinePickkingState {

    @Override
    protected void initialize() {
        setNextClass(SettleUnsettleLineTypeState.class);
        setPrevClass(SettleUnsettleLineTypeState.class);
    }

    @Override
    protected void onResult(PaintContext context) {
        OriLine oriLine = context.getPickedLines().get(0);
        oriLine.setType(LineType.getAuxType(oriLine.getType()));
        context.getPickedLines().clear();
    }

}
