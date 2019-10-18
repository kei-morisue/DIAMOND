/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import diamond.controller.paint.context.Context;
import diamond.controller.paint.context.PaintScreenContext;
import diamond.model.geom.element.cp.Cp;
import diamond.model.geom.element.cp.OriLine;
import diamond.model.palette.cp.editor.LineRemover;

/**
 * @author long_
 *
 */
public class DeleteLineState extends OriLinePickkingState {

    @Override
    protected void initialize() {
        setNextClass(DeleteLineState.class);
        setPrevClass(DeleteLineState.class);
    }

    @Override
    protected void onResult(Context context) {
        PaintScreenContext paintScreenContext = context.getPaintScreenContext();
        OriLine oriLine = paintScreenContext.getPickedLines()
                .get(0);
        Cp cp = context.getPalette().getCP();
        if (oriLine != null) {
            LineRemover.remove(oriLine,
                    cp.getLines());
        }
        paintScreenContext.getPickedLines().clear();
    }

}
