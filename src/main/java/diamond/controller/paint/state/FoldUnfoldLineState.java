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
public class FoldUnfoldLineState extends OriLinePickkingState {
    @Override
    protected void initialize() {
        setNextClass(FoldUnfoldLineState.class);
        setPrevClass(FoldUnfoldLineState.class);
    }

    @Override
    protected void onResult(PaintContext context) {

        OriLine oriLine = context.getPickedLines().get(0);
        LineType type = oriLine.getType();
        if (type != LineType.AUX) {
            context.memorizedLineType = type;
            oriLine.setType(LineType.AUX);
        } else {
            oriLine.setType(context.memorizedLineType);
        }
        context.getPickedLines().clear();
    }

}
