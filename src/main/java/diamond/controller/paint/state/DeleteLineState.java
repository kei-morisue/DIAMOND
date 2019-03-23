/**
 * DIAMOND - Origami Diagram Editor
 * Copyright (C) 2018-2019 Kei Morisue
 */
package diamond.controller.paint.state;

import diamond.controller.paint.PaintContext;
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
    protected void onResult(PaintContext context) {
        OriLine oriLine = context.getPickedLines().get(0);
        Cp cp = context.palette.getCP();
        if (oriLine != null) {
            LineRemover.removeLine(oriLine,
                    cp.getLines());
        }
        cp.rebuildModel();
        context.getPickedLines().clear();
    }

}
