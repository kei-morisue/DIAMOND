/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state.modifycontour;

import diamond.controller.paint.PaintContext;
import diamond.controller.paint.state.OriLinePickkingState;
import diamond.model.geom.element.LineType;
import diamond.model.geom.element.cp.OriLine;

/**
 * @author long_
 *
 */
public class OriLine0PickkingState extends OriLinePickkingState {
    @Override
    protected void initialize() {
        setPrevClass(this.getClass());
        setNextClass(this.getClass());
    }

    @Override
    protected void onResult(PaintContext context) {
        OriLine pointedOriLine = context.pointedOriLine;
        if (pointedOriLine != null) {
            pointedOriLine.setType(LineType.CUT);
        }
    }

}
